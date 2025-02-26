package cn.eu.generate.utils;

import cn.dev33.satoken.spring.SpringMVCUtil;
import cn.eu.common.constants.Constants;
import cn.eu.common.enums.EuFrontHeader;
import cn.eu.generate.constants.GenConstant;
import cn.eu.generate.domain.GenTable;
import cn.eu.generate.domain.GenTableColumn;
import cn.eu.generate.model.dto.GenerateTemplateDto;
import cn.hutool.core.util.StrUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.StringWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhaoeryu
 * @since 2023/6/27
 */
public class VelocityHelper {

    public static void init() {
        Properties p = new Properties();
        try {
            // 加载classpath目录下的vm文件
            p.setProperty("resource.loader.file.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            p.setProperty(Velocity.INPUT_ENCODING, GenConstant.UTF8);
            // 初始化Velocity引擎，指定配置Properties
            Velocity.init(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取vm模版列表
     */
    public static List<GenerateTemplateDto> getTemplates() {
        String requestHeaderFront = SpringMVCUtil.getRequest().getHeader(Constants.REQUEST_HEADER_FRONT_KEY);

        List<GenerateTemplateDto> list = new ArrayList<>();
        // java
        list.add(new GenerateTemplateDto("vm/java/Controller.vm", "Controller.java", GenConstant.TPL_FILE_TYPE_JAVA));
        list.add(new GenerateTemplateDto("vm/java/ServiceImpl.vm", "ServiceImpl.java", GenConstant.TPL_FILE_TYPE_JAVA));
        list.add(new GenerateTemplateDto("vm/java/IService.vm", "Service.java", GenConstant.TPL_FILE_TYPE_JAVA));
        list.add(new GenerateTemplateDto("vm/java/Mapper.vm", "Mapper.java", GenConstant.TPL_FILE_TYPE_JAVA));
        list.add(new GenerateTemplateDto("vm/java/Entity.vm", "Entity.java", GenConstant.TPL_FILE_TYPE_JAVA));
        list.add(new GenerateTemplateDto("vm/java/QueryCriteria.vm", "QueryCriteria.java", GenConstant.TPL_FILE_TYPE_JAVA));
        // xml
        list.add(new GenerateTemplateDto("vm/xml/Mapper.vm", "Mapper.xml", GenConstant.TPL_FILE_TYPE_XML));
        // sql
        list.add(new GenerateTemplateDto("vm/sql/sql.vm", "sql", GenConstant.TPL_FILE_TYPE_SQL));
        // properties
        list.add(new GenerateTemplateDto("vm/i18n/messages.vm", "messages.properties", GenConstant.TPL_FILE_TYPE_PROPERTIES, true));
        list.add(new GenerateTemplateDto("vm/i18n/messages_zh_CN.vm", "messages_zh_CN.properties", GenConstant.TPL_FILE_TYPE_PROPERTIES, true));
        list.add(new GenerateTemplateDto("vm/i18n/messages_en_US.vm", "messages_en_US.properties", GenConstant.TPL_FILE_TYPE_PROPERTIES, true));

        if (EuFrontHeader.VUE2.getDesc().equals(requestHeaderFront)) {
            // vue
            list.add(new GenerateTemplateDto("vm/vue/table_vxe.vm", "index.vue", GenConstant.TPL_FILE_TYPE_VUE));
            list.add(new GenerateTemplateDto("vm/vue/editDialog.vm", "editDialog.vue", GenConstant.TPL_FILE_TYPE_VUE));
            list.add(new GenerateTemplateDto("vm/vue/api.vm", "api.js", GenConstant.TPL_FILE_TYPE_JS));
            list.add(new GenerateTemplateDto("vm/vue/locale.zh_CN.vm", "locale.zh_CN.js", GenConstant.TPL_FILE_TYPE_JS));
            list.add(new GenerateTemplateDto("vm/vue/locale.en_US.vm", "local.en_US.js", GenConstant.TPL_FILE_TYPE_JS));
        } else if (EuFrontHeader.VUE3.getDesc().equals(requestHeaderFront)) {
            // vue3
            list.add(new GenerateTemplateDto("vm/vue3/vue.vm", "index.vue", GenConstant.TPL_FILE_TYPE_VUE));
            list.add(new GenerateTemplateDto("vm/vue3/editDialog.vm", "editDialog.vue", GenConstant.TPL_FILE_TYPE_VUE));
            list.add(new GenerateTemplateDto("vm/vue3/api.vm", "api.js", GenConstant.TPL_FILE_TYPE_JS));
        }
        return list;
    }

    /**
     * 渲染vm模版
     */
    public static List<GenerateTemplateDto> render(VelocityContext velocityContext) {
        return render(getTemplates(), velocityContext);
    }
    public static List<GenerateTemplateDto> render(List<GenerateTemplateDto> templates, VelocityContext velocityContext) {
        // 加载宏
        getMarcoTemplate().forEach(item -> {
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(item, GenConstant.UTF8);
            tpl.merge(velocityContext, sw);
        });

        // 渲染模版
        templates.forEach(item -> {
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(item.getPath(), GenConstant.UTF8);
            tpl.merge(velocityContext, sw);
            item.setCode(sw.toString());
        });
        return templates;
    }

    public static List<String> getMarcoTemplate() {
        List<String> list = new ArrayList<>();
        list.add("vm/macro/elFormItem.vm");
        list.add("vm/macro/elTableQueryItem.vm");
        list.add("vm/macro/vue3_elFormItem.vm");
        return list;
    }

    public static VelocityContext fillContext(GenTable genTable, List<GenTableColumn> genTableColumns) {
        String moduleName = StrUtil.blankToDefault(genTable.getModuleName(), GenConstant.DEFAULT_MODULE_NAME);

        String permissionPrefix = "";
        String apiPath = "";
        if (StrUtil.isNotBlank(genTable.getFuncGroup())) {
            permissionPrefix = genTable.getFuncGroup() + ":";
            apiPath = genTable.getFuncGroup() + "/";
        }
        permissionPrefix += genTable.getTableName();
        apiPath += genTable.getTableName();
        permissionPrefix = StrUtil.toCamelCase(permissionPrefix);
        apiPath = StrUtil.toCamelCase(apiPath);

        // 过滤掉基类字段
        List<GenTableColumn> genColumns = genTableColumns.stream().filter(item -> !GenUtil.isFieldBaseEntity(item.getColumnName())).collect(Collectors.toList());

        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("package", StrUtil.blankToDefault(genTable.getPackageName(), GenConstant.DEFAULT_PACKAGE_NAME));
        velocityContext.put("module", moduleName);
        velocityContext.put("funcGroup", genTable.getFuncGroup());
        // 如果没有配置作者，则默认当前系统用户
        velocityContext.put("author", StrUtil.blankToDefault(genTable.getAuthor(), System.getProperty("user.name")));
        velocityContext.put("date", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        velocityContext.put("className", GenUtil.underlineToCamel(genTable.getTableName()));
        velocityContext.put("ClassName", GenUtil.underlineToBigCamel(genTable.getTableName()));
        velocityContext.put("classNameKebabCase", GenUtil.underlineToKebabCase(genTable.getTableName()));
        velocityContext.put("tableComment", genTable.getTableComment());
        velocityContext.put("permissionPrefix", permissionPrefix);
        velocityContext.put("apiPath", apiPath);
        velocityContext.put("tableName", genTable.getTableName());
        velocityContext.put("pkColumnType", GenUtil.getPkColumnType(genTableColumns));
        velocityContext.put("autoPk", GenUtil.isAutoPk(genTableColumns));
        velocityContext.put("delShowField", StrUtil.blankToDefault(genTable.getDelShowField(), GenUtil.underlineToCamel(genColumns.get(0).getColumnName())));
        velocityContext.put("entityColumns", genColumns);
        velocityContext.put("columns", genTableColumns);
        // 字典key列表
        velocityContext.put("dicts", genTableColumns.stream()
                        .map(GenTableColumn::getDictKey)
                        .filter(StrUtil::isNotBlank)
                        .distinct()
                        .collect(Collectors.toList()));

        return velocityContext;
    }


}
