package cn.eu.system.service.impl;

import cn.eu.common.core.service.impl.EuServiceImpl;
import cn.eu.common.enums.EnableFlag;
import cn.eu.common.model.PageResult;
import cn.eu.common.utils.EasyExcelHelper;
import cn.eu.common.utils.MpQueryHelper;
import cn.eu.common.utils.ValidateUtil;
import cn.eu.common.utils.easyexcel.EasyExcelWriteSheet;
import cn.eu.system.domain.SysDict;
import cn.eu.system.domain.SysDictDetail;
import cn.eu.system.mapper.SysDictMapper;
import cn.eu.system.model.dto.ImportResult;
import cn.eu.system.model.query.SysDictQueryCriteria;
import cn.eu.system.service.ISysDictDetailService;
import cn.eu.system.service.ISysDictService;
import cn.eu.system.utils.ImportModeHandleTemplate;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhaoeryu
 * @since 2023/5/31
 */
@Service
public class SysDictServiceImpl extends EuServiceImpl<SysDictMapper, SysDict> implements ISysDictService {

    @Autowired
    SysDictMapper SysDictMapper;
    @Autowired
    ISysDictDetailService sysDictDetailService;

    @Override
    public PageResult<SysDict> page(SysDictQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        return PageResult.of(list(criteria));
    }

    @Override
    public List<SysDict> list(SysDictQueryCriteria criteria) {
        return list(MpQueryHelper.buildQueryWrapper(criteria, SysDict.class));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateDict(SysDict entity) {
        updateById(entity);
        // 更新字典的dictKey
        sysDictDetailService.update(new LambdaUpdateWrapper<SysDictDetail>()
            .eq(SysDictDetail::getPid, entity.getId())
            .set(SysDictDetail::getDictKey, entity.getDictKey())
        );
    }

    @Override
    public void exportTemplate(HttpServletResponse response) throws IOException {
        List<String> exportExcludeFieldNames = buildExportExcludeFieldNames();

        // sheet1
        WriteSheet writeSheet = EasyExcel.writerSheet(0, "sheet1")
                .head(SysDict.class)
                .excludeColumnFieldNames(exportExcludeFieldNames)
                .build();
        EasyExcelWriteSheet sheet1 = EasyExcelWriteSheet.of(writeSheet, null)
                .registerStandardWriteHandler();

        // sheet2
        writeSheet = EasyExcel.writerSheet(1, "示例数据")
                .head(SysDict.class)
                .excludeColumnFieldNames(exportExcludeFieldNames)
                .build();
        SysDict exampleEntity = buildExampleEntity();
        EasyExcelWriteSheet sheet2 = EasyExcelWriteSheet.of(writeSheet, Collections.singletonList(exampleEntity))
                .registerStandardWriteHandler();

        EasyExcelHelper.export(response, sheet1, sheet2);
    }

    @Override
    public ImportResult importData(MultipartFile file, Integer importMode) throws IOException {
        ImportModeHandleTemplate<SysDict, String> importModeHandleTemplate = new ImportModeHandleTemplate<SysDict, String>(importMode, SysDict::getDictKey) {

            @Override
            protected void valid(List<SysDict> list) {
                list.forEach(ValidateUtil::valid);
            }

            @Override
            public void add(List<SysDict> list) {
                saveBatch(list, list.size());
            }

            @Override
            public void update(List<SysDict> list) {
                updateBatchById(list, list.size());
            }

            @Override
            protected SysDict buildUpdateItem(SysDict dbItem, SysDict excelItem) {
                SysDict updateItem = new SysDict();
                updateItem.setId(dbItem.getId());
                updateItem.setDictKey(dbItem.getDictKey());
                updateItem.setStatus(excelItem.getStatus());
                updateItem.setRemark(excelItem.getRemark());
                return updateItem;
            }

            @Override
            protected List<SysDict> getDbList(List<SysDict> list) {
                return list(new LambdaUpdateWrapper<SysDict>()
                    .in(SysDict::getDictKey, list.stream().map(SysDict::getDictKey).collect(Collectors.toSet()))
                );
            }
        };

        EasyExcelHelper.importData(file, SysDict.class, importModeHandleTemplate::handle);

        return importModeHandleTemplate.getResult();
    }

    private List<String> buildExportExcludeFieldNames() {
        return Arrays.asList(
            "createTime"
        );
    }

    private SysDict buildExampleEntity() {
        SysDict entity = new SysDict();
        entity.setId(-1);
        entity.setDictKey("sex");
        entity.setStatus(EnableFlag.ENABLED);
        entity.setRemark("性别");
        return entity;
    }

    @Override
    public void removeDictByIds(List<Integer> ids) {
        removeByIds(ids);

        // 删除字典详情
        sysDictDetailService.remove(new LambdaUpdateWrapper<SysDictDetail>()
            .in(SysDictDetail::getPid, ids)
        );
    }
}
