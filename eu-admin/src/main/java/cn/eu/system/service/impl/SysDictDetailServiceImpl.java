package cn.eu.system.service.impl;

import cn.eu.common.base.service.impl.EuServiceImpl;
import cn.eu.common.enums.SysDictDetailStatus;
import cn.eu.common.enums.SysDictStatus;
import cn.eu.common.model.PageResult;
import cn.eu.common.utils.EasyExcelHelper;
import cn.eu.common.utils.MpQueryHelper;
import cn.eu.common.utils.ValidateUtil;
import cn.eu.common.utils.easyexcel.EasyExcelCellItem;
import cn.eu.common.utils.easyexcel.EasyExcelUtil;
import cn.eu.common.utils.easyexcel.EasyExcelWriteSheet;
import cn.eu.system.domain.SysDept;
import cn.eu.system.domain.SysDict;
import cn.eu.system.domain.SysDictDetail;
import cn.eu.system.domain.SysUser;
import cn.eu.system.mapper.SysDictDetailMapper;
import cn.eu.system.model.dto.ImportResult;
import cn.eu.system.model.query.SysDictDetailQueryCriteria;
import cn.eu.system.service.ISysDictDetailService;
import cn.eu.system.service.ISysDictService;
import cn.eu.system.utils.ImportModeHandleTemplate;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhaoeryu
 * @since 2023/5/31
 */
@Service
public class SysDictDetailServiceImpl extends EuServiceImpl<SysDictDetailMapper, SysDictDetail> implements ISysDictDetailService {

    @Autowired
    SysDictDetailMapper SysDictDetailMapper;
    @Autowired
    ISysDictService sysDictService;

    @Override
    public PageResult<SysDictDetail> page(SysDictDetailQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        return PageResult.of(list(criteria));
    }

    @Override
    public List<SysDictDetail> list(SysDictDetailQueryCriteria criteria) {
        return list(MpQueryHelper.buildQueryWrapper(criteria, SysDictDetail.class));
    }

    @Override
    public void exportTemplate(HttpServletResponse response) throws IOException {
        List<String> exportExcludeFieldNames = buildExportExcludeFieldNames();

        // sheet1
        WriteSheet writeSheet = EasyExcel.writerSheet(0, "sheet1")
                .head(SysDictDetail.class)
                .excludeColumnFieldNames(exportExcludeFieldNames)
                .build();
        EasyExcelWriteSheet sheet1 = EasyExcelWriteSheet.of(writeSheet, null)
                .registerStandardWriteHandler();

        // sheet2
        writeSheet = EasyExcel.writerSheet(1, "示例数据")
                .head(SysDictDetail.class)
                .excludeColumnFieldNames(exportExcludeFieldNames)
                .build();
        SysDictDetail exampleEntity = buildExampleEntity();
        EasyExcelWriteSheet sheet2 = EasyExcelWriteSheet.of(writeSheet, Collections.singletonList(exampleEntity))
                .registerStandardWriteHandler();

        // 导出
        EasyExcelHelper.export(response,
                sheet1,
                sheet2
        );
    }

    @Override
    public ImportResult importData(MultipartFile file, Integer importMode, Integer dictId) throws IOException {
        SysDict dict = sysDictService.getById(dictId);
        Assert.notNull(dict, "字典不存在");

        ImportModeHandleTemplate<SysDictDetail, String> importModeHandleTemplate = new ImportModeHandleTemplate<SysDictDetail, String>(importMode, item -> item.getDictKey() + "_" + item.getDictLabel()) {

            @Override
            protected void valid(List<SysDictDetail> list) {
                list.forEach(item -> {
                    // 设置字典ID
                    item.setPid(dict.getId());
                    item.setDictKey(dict.getDictKey());
                    // 校验
                    ValidateUtil.valid(item);
                });
            }

            @Override
            public void add(List<SysDictDetail> list) {
                saveBatch(list, list.size());
            }

            @Override
            public void update(List<SysDictDetail> list) {
                updateBatchById(list, list.size());
            }

            @Override
            protected SysDictDetail buildUpdateItem(SysDictDetail dbItem, SysDictDetail excelItem) {
                SysDictDetail updateItem = new SysDictDetail();
                updateItem.setId(dbItem.getId());
                updateItem.setDictLabel(dbItem.getDictLabel());
                updateItem.setDictValue(dbItem.getDictValue());
                updateItem.setStatus(excelItem.getStatus());
                updateItem.setSortNum(excelItem.getSortNum());
                updateItem.setRemark(excelItem.getRemark());
                return updateItem;
            }

            @Override
            protected List<SysDictDetail> getDbList(List<SysDictDetail> list) {
                Set<Integer> pidSet = list.stream().map(SysDictDetail::getPid).collect(Collectors.toSet());
                return list(new LambdaUpdateWrapper<SysDictDetail>()
                    .in(SysDictDetail::getPid, pidSet)
                );
            }
        };

        EasyExcelHelper.importData(file, SysDictDetail.class, importModeHandleTemplate::handle);

        return importModeHandleTemplate.getResult();
    }

    private List<String> buildExportExcludeFieldNames() {
        return Arrays.asList(
                "pid",
                "dictKey",
                "createTime"
        );
    }

    private SysDictDetail buildExampleEntity() {
        SysDictDetail entity = new SysDictDetail();
        entity.setPid(-1);
        entity.setDictKey("sex");
        entity.setDictLabel("男");
        entity.setDictValue("1");
        entity.setStatus(SysDictDetailStatus.NORMAL.getValue());
        entity.setSortNum(99);
        entity.setRemark("这里可以写备注");
        return entity;
    }
}
