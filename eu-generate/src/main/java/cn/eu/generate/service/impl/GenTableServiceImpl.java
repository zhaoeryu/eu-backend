package cn.eu.generate.service.impl;

import cn.eu.common.core.service.impl.EuServiceImpl;
import cn.eu.common.model.PageResult;
import cn.eu.generate.constants.GenConstant;
import cn.eu.generate.domain.GenTable;
import cn.eu.generate.domain.GenTableColumn;
import cn.eu.generate.enums.GenMode;
import cn.eu.generate.mapper.GenTableMapper;
import cn.eu.generate.model.dto.GenerateTemplateDto;
import cn.eu.generate.model.vo.TableInfoVo;
import cn.eu.generate.service.IGenTableColumnService;
import cn.eu.generate.service.IGenTableService;
import cn.eu.generate.model.query.GenTableQueryCriteria;
import cn.eu.generate.utils.FieldTypeMappingUtil;
import cn.eu.generate.utils.GenUtil;
import cn.eu.generate.utils.VelocityHelper;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author zhaoeryu
 * @since 2023/6/27
 */
@Slf4j
@Service
public class GenTableServiceImpl extends EuServiceImpl<GenTableMapper, GenTable> implements IGenTableService {

    @Autowired
    GenTableMapper genTableMapper;
    @Autowired
    IGenTableColumnService genTableColumnService;

    @Override
    public PageResult<GenTable> listTables(GenTableQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        List<GenTable> list = genTableMapper.selectDbTableList(criteria);
        return PageResult.of(list);
    }

    @Override
    public List<GenerateTemplateDto> preview(String tableName) {
        VelocityHelper.init();

        // 从数据查询列信息
        List<GenTableColumn> genTableColumns = getGenTableColumns(tableName);

        // 从数据查询表信息
        GenTable genTable = getGenTable(tableName);

        // 设置模版上下文环境
        VelocityContext velocityContext = VelocityHelper.fillContext(genTable, genTableColumns);

        // 渲染模版
        return VelocityHelper.render(velocityContext);
    }

    @Override
    public GenTable getGenTable(String tableName) {
        GenTable genTable = getOne(new LambdaQueryWrapper<GenTable>()
                .eq(GenTable::getTableName, tableName)
        );
        if (genTable == null) {
            genTable = genTableMapper.selectDbTableByTableName(tableName);
        }
        genTable.setModuleName(StrUtil.blankToDefault(genTable.getModuleName(), GenConstant.DEFAULT_MODULE_NAME));
        genTable.setPackageName(StrUtil.blankToDefault(genTable.getPackageName(), GenConstant.DEFAULT_PACKAGE_NAME));
        genTable.setAuthor(StrUtil.blankToDefault(genTable.getAuthor(), System.getProperty("user.name")));
        genTable.setGenMode(genTable.getGenMode() == null ? GenMode.GENERATE.ordinal() : genTable.getGenMode());

        return genTable;
    }

    @Override
    public List<GenTableColumn> getGenTableColumns(String tableName) {
        List<GenTableColumn> genTableColumns = genTableColumnService.listByTableName(tableName);
        if (CollUtil.isEmpty(genTableColumns)) {
            genTableColumns = genTableMapper.selectDbTableColumnList(tableName);
        }
        genTableColumns.forEach(this::fillTableColumnDefaultValue);
        return genTableColumns;
    }

    private void fillTableColumnDefaultValue(GenTableColumn item) {
        if (StrUtil.isBlank(item.getJavaType())) {
            item.setJavaType(FieldTypeMappingUtil.getJavaType(item.getColumnType()));
        }
        if (StrUtil.isBlank(item.getJavaField())) {
            item.setJavaField(StrUtil.toCamelCase(item.getColumnName()));
        }
        if (item.getColumnLength() == null) {
            item.setColumnLength(FieldTypeMappingUtil.getFieldLength(item.getColumnType()));
        }
        if (item.getExcelExport() == null) {
            item.setExcelExport(GenUtil.isFieldExport(item.getColumnName()));
        }
        if (item.getTableShow() == null) {
            item.setTableShow(GenUtil.isFieldTableShow(item.getColumnName()));
        }
        if (item.getFormShow() == null) {
            item.setFormShow(GenUtil.isFieldFormShow(item.getColumnName()));
        }
        if (StrUtil.isBlank(item.getQueryType())) {
            item.setQueryType(null);
        }
        if (item.getDefaultVisible() == null) {
            item.setDefaultVisible(
                    GenUtil.isFieldTableShow(item.getColumnName()) &&
                    GenUtil.isFieldInTableVisible(item.getColumnName())
            );
        }
        if (item.getAreaQuery() == null) {
            item.setAreaQuery(false);
        }
        if (item.getTableHeaderQuery() == null) {
            item.setTableHeaderQuery(false);
        }
        if (StrUtil.isBlank(item.getFormType())) {
            String formType = null;
            switch (item.getJavaType()) {
                case "Integer":
                case "Long":
                case "Float":
                case "Double":
                case "BigDecimal":
                    formType = "number";
                    break;
                case "LocalDateTime":
                    formType = "datetime";
                    break;
                case "LocalDate":
                    formType = "date";
                    break;
                case "Boolean":
                    formType = "switch";
                    break;
                default:
                    // 其他类型默认为input
            }
            item.setFormType(formType);
        }
        if (item.getId() == null) {
            // 如果是基础字段并且非手动配置，那么默认可以为空
            boolean isFieldBaseEntity = GenUtil.isFieldBaseEntity(item.getColumnName());
            if (isFieldBaseEntity) {
                item.setNotNull(false);
            }
        }
    }

    @Override
    public TableInfoVo getTableInfo(String tableName) {
        GenTable genTable = getGenTable(tableName);

        List<GenTableColumn> genTableColumns = getGenTableColumns(tableName);

        TableInfoVo tableInfoVo = new TableInfoVo();
        tableInfoVo.setTable(genTable);
        tableInfoVo.setColumns(genTableColumns);
        return tableInfoVo;
    }

    @Override
    public boolean saveTable(GenTable entity) {
        return StrUtil.isBlank(entity.getId()) ? save(entity) : updateById(entity);
    }

    @Override
    public void saveColumns(List<GenTableColumn> entityList) {
        genTableColumnService.saveOrUpdateBatch(entityList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void sync(String tableName) {
        // 表
        GenTable dbTable = genTableMapper.selectDbTableByTableName(tableName);
        GenTable cacheTable = getOne(new LambdaQueryWrapper<GenTable>().eq(GenTable::getTableName, tableName));

        // 字段
        List<GenTableColumn> dbColumns = genTableMapper.selectDbTableColumnList(tableName);
        List<GenTableColumn> cacheColumns = genTableColumnService.listByTableName(tableName);

        if (dbTable == null) {
            // 数据库表已被删除

            // 删除表配置
            remove(new LambdaQueryWrapper<GenTable>()
                    .eq(GenTable::getTableName, tableName)
            );
            // 删除字段配置
            genTableColumnService.remove(new LambdaQueryWrapper<GenTableColumn>()
                    .eq(GenTableColumn::getTableName, tableName)
            );
            return;
        }

        if (cacheTable != null) {
            // 更新表配置
            cacheTable.setTableName(dbTable.getTableName());
            cacheTable.setTableComment(dbTable.getTableComment());
            cacheTable.setCreateTime(dbTable.getCreateTime());
            cacheTable.setUpdateTime(dbTable.getUpdateTime());

            updateById(cacheTable);
        }

        if (CollUtil.isEmpty(cacheColumns)) {
            // 如果还没有配置字段，则不用处理
            return;
        }
        Map<String, GenTableColumn> dbColumnMap = new HashMap<>();
        dbColumns.forEach(item -> dbColumnMap.put(item.getColumnName(), item));

        Map<String, GenTableColumn> cacheColumnMap = new HashMap<>();
        cacheColumns.forEach(item -> cacheColumnMap.put(item.getColumnName(), item));

        // 找出需要删除的字段
        List<GenTableColumn> deleteColumns = cacheColumns.stream()
                .filter(item -> !dbColumnMap.containsKey(item.getColumnName()))
                .collect(Collectors.toList());

        if (CollUtil.isNotEmpty(deleteColumns)) {
            deleteColumns.forEach(item -> {
                cacheColumnMap.remove(item.getColumnName());
                cacheColumns.remove(item);
            });
            List<String> deleteColumnIds = deleteColumns.stream()
                    .map(GenTableColumn::getId)
                    .distinct()
                    .collect(Collectors.toList());
            genTableColumnService.removeByIds(deleteColumnIds);
        }

        // 找出需要新增的字段
        List<GenTableColumn> addColumns = dbColumns.stream()
                .filter(item -> !cacheColumnMap.containsKey(item.getColumnName()))
                .peek(this::fillTableColumnDefaultValue)
                .collect(Collectors.toList());
        if (CollUtil.isNotEmpty(addColumns)) {
            genTableColumnService.saveBatch(addColumns);
        }

        // 找出需要更新的字段
        cacheColumns.forEach(item -> {
            GenTableColumn dbColumn = dbColumnMap.get(item.getColumnName());

            item.setTableName(dbColumn.getTableName());
            item.setColumnName(dbColumn.getColumnName());
            item.setColumnComment(dbColumn.getColumnComment());
            item.setColumnType(dbColumn.getColumnType());
            item.setColumnKey(dbColumn.getColumnKey());
            item.setAutoPk(dbColumn.getAutoPk());
            item.setNotNull(dbColumn.getNotNull());
            item.setColumnSort(dbColumn.getColumnSort());

            item.setJavaType(FieldTypeMappingUtil.getJavaType(item.getColumnType()));
            item.setJavaField(StrUtil.toCamelCase(item.getColumnName()));
            item.setColumnLength(FieldTypeMappingUtil.getFieldLength(item.getColumnType()));
        });
        if (CollUtil.isNotEmpty(cacheColumns)) {
            genTableColumnService.updateBatchById(cacheColumns);
        }
    }

    @Override
    public void generator(String tableName, HttpServletResponse response) throws IOException {
        List<GenerateTemplateDto> generateTemplateDtoList = preview(tableName);
        if (CollUtil.isEmpty(generateTemplateDtoList)) {
            throw new RuntimeException("没有可生成的文件");
        }

        GenTable genTable = getGenTable(tableName);
        if (GenMode.GENERATE.ordinal() == genTable.getGenMode()) {
            // 代码生成
            String rootPath = System.getProperty("user.dir");
            for (GenerateTemplateDto generateTemplateDto : generateTemplateDtoList) {
                // 生成文件路径
                String genFilePath = getGenFilePath(rootPath, generateTemplateDto, genTable);
                System.out.println(genFilePath);

                // 写入到文件
                FileUtil.writeUtf8String(generateTemplateDto.getCode(), genFilePath);
            }

            // 响应成功
            GenUtil.responseSuccess(response);
        } else if (GenMode.DOWNLOAD.ordinal() == genTable.getGenMode()) {
            // 代码生成打包下载

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ZipOutputStream zip = new ZipOutputStream(outputStream);

            // 填充zip
            String rootPath = StrUtil.subAfter(System.getProperty("user.dir"), "/", true);
            for (GenerateTemplateDto generateTemplateDto : generateTemplateDtoList) {
                // 生成文件路径
                String genFilePath = getGenFilePath(rootPath, generateTemplateDto, genTable);
                System.out.println(genFilePath);

                // 添加到zip
                zip.putNextEntry(new ZipEntry(genFilePath));
                IOUtils.write(generateTemplateDto.getCode(), zip, GenConstant.UTF8);
                zip.flush();
                zip.closeEntry();
            }
            IOUtils.closeQuietly(zip);

            // 写入到response
            GenUtil.write(outputStream.toByteArray(), response);
        } else {
            throw new RuntimeException("生成模式错误");
        }
    }

    private String getGenFilePath(String rootPath, GenerateTemplateDto generateTemplateDto, GenTable genTable) {
        // /${rootPath}/${moduleName}/src/main/java/${packageName}/controller/${ClassName}Controller.java
        // /${rootPath}/${moduleName}/src/main/java/${packageName}/service/I${ClassName}Service.java
        // /${rootPath}/${moduleName}/src/main/java/${packageName}/service/impl/${ClassName}ServiceImpl.java
        // /${rootPath}/${moduleName}/src/main/java/${packageName}/mapper/${ClassName}Mapper.java
        // /${rootPath}/${moduleName}/src/main/java/${packageName}/domain/${ClassName}.java
        // /${rootPath}/${moduleName}/src/main/java/${packageName}/model/query/${ClassName}QueryCriteria.java
        // /${rootPath}/${moduleName}/src/main/resources/mapper/${ClassName}Mapper.xml

        // /${rootPath}/generate/views/${className}/index.vue
        // /${rootPath}/generate/api/${className}.js
        // /${rootPath}/generate/sql/${className}.js
        StringJoiner filePathJoiner = new StringJoiner(File.separator)
                .add(rootPath);

        String funcGroup = StrUtil.blankToDefault(genTable.getFuncGroup(), null);
        funcGroup = StrUtil.isBlank(funcGroup) ? "" : funcGroup + File.separator;

        switch (generateTemplateDto.getPath()) {
            case "vm/java/Controller.vm":
                filePathJoiner.add(genTable.getModuleName())
                        .add("src")
                        .add("main")
                        .add("java")
                        .add(genTable.getPackageName().replace(".", File.separator))
                        .add("controller")
                        .add(GenUtil.underlineToBigCamel(genTable.getTableName()) + "Controller.java");
                break;
            case "vm/java/IService.vm":
                filePathJoiner.add(genTable.getModuleName())
                        .add("src")
                        .add("main")
                        .add("java")
                        .add(genTable.getPackageName().replace(".", File.separator))
                        .add("service")
                        .add("I" + GenUtil.underlineToBigCamel(genTable.getTableName()) + "Service.java");
                break;
            case "vm/java/ServiceImpl.vm":
                filePathJoiner.add(genTable.getModuleName())
                        .add("src")
                        .add("main")
                        .add("java")
                        .add(genTable.getPackageName().replace(".", File.separator))
                        .add("service")
                        .add("impl")
                        .add(GenUtil.underlineToBigCamel(genTable.getTableName()) + "ServiceImpl.java");
                break;
            case "vm/java/Mapper.vm":
                filePathJoiner.add(genTable.getModuleName())
                        .add("src")
                        .add("main")
                        .add("java")
                        .add(genTable.getPackageName().replace(".", File.separator))
                        .add("mapper")
                        .add(GenUtil.underlineToBigCamel(genTable.getTableName()) + "Mapper.java");
                break;
            case "vm/java/Entity.vm":
                filePathJoiner.add(genTable.getModuleName())
                        .add("src")
                        .add("main")
                        .add("java")
                        .add(genTable.getPackageName().replace(".", File.separator))
                        .add("domain")
                        .add(GenUtil.underlineToBigCamel(genTable.getTableName()) + ".java");
                break;
            case "vm/java/QueryCriteria.vm":
                filePathJoiner.add(genTable.getModuleName())
                        .add("src")
                        .add("main")
                        .add("java")
                        .add(genTable.getPackageName().replace(".", File.separator))
                        .add("model")
                        .add("query")
                        .add(GenUtil.underlineToBigCamel(genTable.getTableName()) + "QueryCriteria.java");
                break;
            case "vm/xml/Mapper.vm":
                filePathJoiner.add(genTable.getModuleName())
                        .add("src")
                        .add("main")
                        .add("resources")
                        .add("mapper")
                        .add(GenUtil.underlineToBigCamel(genTable.getTableName()) + "Mapper.xml");
                break;
            case "vm/sql/sql.vm":
                filePathJoiner.add("generate")
                        .add("sql")
                        .add(GenUtil.underlineToCamel(genTable.getTableName()) + ".sql");
                break;
            case "vm/i18n/messages.vm":
                filePathJoiner.add(genTable.getModuleName())
                        .add("src")
                        .add("main")
                        .add("resources")
                        .add("i18n")
                        .add("messages.properties");
                break;
            case "vm/i18n/messages_zh_CN.vm":
                filePathJoiner.add(genTable.getModuleName())
                        .add("src")
                        .add("main")
                        .add("resources")
                        .add("i18n")
                        .add("messages_zh_CN.properties");
                break;
            case "vm/i18n/messages_en_US.vm":
                filePathJoiner.add(genTable.getModuleName())
                        .add("src")
                        .add("main")
                        .add("resources")
                        .add("i18n")
                        .add("messages_en_US.properties");
                break;

            // vue2
            case "vm/vue/table_vxe.vm":
                filePathJoiner.add("generate")
                        .add("vue2")
                        .add("views")
                        .add(funcGroup + GenUtil.underlineToCamel(genTable.getTableName()))
                        .add("index.vue");
                break;
            case "vm/vue/editDialog.vm":
                filePathJoiner.add("generate")
                        .add("vue2")
                        .add("views")
                        .add(funcGroup + GenUtil.underlineToCamel(genTable.getTableName()))
                        .add(GenUtil.underlineToBigCamel(genTable.getTableName()) + "EditDialog.vue");
                break;
            case "vm/vue/api.vm":
                filePathJoiner.add("generate")
                        .add("vue2")
                        .add("api")
                        .add(funcGroup + GenUtil.underlineToCamel(genTable.getTableName()) + ".js");
                break;
            case "vm/vue/locale.zh_CN.vm":
                filePathJoiner.add("generate")
                        .add("vue2")
                        .add("views")
                        .add(funcGroup + GenUtil.underlineToCamel(genTable.getTableName()))
                        .add("locale")
                        .add("zh_CN.js");
                break;
            case "vm/vue/locale.en_US.vm":
                filePathJoiner.add("generate")
                        .add("vue2")
                        .add("views")
                        .add(funcGroup + GenUtil.underlineToCamel(genTable.getTableName()))
                        .add("locale")
                        .add("en_US.js");
                break;

            // vue3
            case "vm/vue3/vue.vm":
                filePathJoiner.add("generate")
                        .add("vue3")
                        .add("views")
                        .add(funcGroup + GenUtil.underlineToCamel(genTable.getTableName()))
                        .add("index.vue");
                break;
            case "vm/vue3/editDialog.vm":
                filePathJoiner.add("generate")
                        .add("vue3")
                        .add("views")
                        .add(funcGroup + GenUtil.underlineToCamel(genTable.getTableName()))
                        .add(GenUtil.underlineToBigCamel(genTable.getTableName()) + "EditDialog.vue");
                break;
            case "vm/vue3/api.vm":
                filePathJoiner.add("generate")
                        .add("vue3")
                        .add("api")
                        .add(funcGroup + GenUtil.underlineToCamel(genTable.getTableName()) + ".js");
                break;
            default:
                throw new RuntimeException("未受支持的模版:" + generateTemplateDto.getPath());
        }
        return filePathJoiner.toString();
    }
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("java.io.tmpdir"));
        String projectName = StrUtil.subAfter(System.getProperty("user.dir"), "/", true);
        System.out.println(projectName);
    }
}
