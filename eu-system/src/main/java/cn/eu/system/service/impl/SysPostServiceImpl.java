package cn.eu.system.service.impl;

import cn.eu.common.core.service.impl.EuServiceImpl;
import cn.eu.common.enums.*;
import cn.eu.common.model.PageResult;
import cn.eu.common.utils.EasyExcelHelper;
import cn.eu.common.utils.MpQueryHelper;
import cn.eu.common.utils.ValidateUtil;
import cn.eu.common.utils.easyexcel.EasyExcelWriteSheet;
import cn.eu.system.domain.SysPost;
import cn.eu.system.mapper.SysPostMapper;
import cn.eu.system.utils.ImportModeHandleTemplate;
import cn.eu.system.model.dto.ImportResult;
import cn.eu.system.model.query.SysPostQueryCriteria;
import cn.eu.system.service.ISysPostService;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhaoeryu
 * @since 2023/5/31
 */
@Service
public class SysPostServiceImpl extends EuServiceImpl<SysPostMapper, SysPost> implements ISysPostService {

    @Autowired
    SysPostMapper sysPostMapper;

    @Override
    public PageResult<SysPost> page(SysPostQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        return PageResult.of(list(criteria));
    }

    @Override
    public List<SysPost> list(SysPostQueryCriteria criteria) {
        return list(MpQueryHelper.buildQueryWrapper(criteria, SysPost.class));
    }

    @Override
    public List<Integer> getPostIdsByUserId(String userId) {
        List<SysPost> posts = sysPostMapper.getPostsByUserId(userId, EnableFlag.ENABLED.getValue());
        return posts.stream()
                .map(SysPost::getId)
                .distinct()
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public void exportTemplate(HttpServletResponse response) throws IOException {
        List<String> exportExcludeFieldNames = buildExportExcludeFieldNames();

        // sheet1
        WriteSheet writeSheet = EasyExcel.writerSheet(0, "sheet1")
                .head(SysPost.class)
                .excludeColumnFieldNames(exportExcludeFieldNames)
                .build();
        EasyExcelWriteSheet sheet1 = EasyExcelWriteSheet.of(writeSheet, null)
                .registerStandardWriteHandler();

        // sheet2
        writeSheet = EasyExcel.writerSheet(1, "示例数据")
                .head(SysPost.class)
                .excludeColumnFieldNames(exportExcludeFieldNames)
                .build();
        SysPost exampleEntity = buildExampleEntity();
        EasyExcelWriteSheet sheet2 = EasyExcelWriteSheet.of(writeSheet, Collections.singletonList(exampleEntity))
                .registerStandardWriteHandler();

        EasyExcelHelper.export(response, sheet1, sheet2);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ImportResult importData(MultipartFile file, Integer importMode) throws IOException {
        ImportModeHandleTemplate<SysPost, String> importModeHandleTemplate = new ImportModeHandleTemplate<SysPost, String>(importMode, SysPost::getPostName) {

            @Override
            protected void valid(List<SysPost> list) {
                list.forEach(ValidateUtil::valid);
            }

            @Override
            public void add(List<SysPost> list) {
                saveBatch(list, list.size());
            }

            @Override
            public void update(List<SysPost> list) {
                updateBatchById(list, list.size());
            }

            @Override
            protected SysPost buildUpdateItem(SysPost dbItem, SysPost excelItem) {
                SysPost updateItem = new SysPost();
                updateItem.setId(dbItem.getId());
                updateItem.setPostName(excelItem.getPostName());
                updateItem.setCode(excelItem.getCode());
                updateItem.setStatus(excelItem.getStatus());
                return updateItem;
            }

            @Override
            protected List<SysPost> getDbList(List<SysPost> list) {
                return list(new LambdaQueryWrapper<SysPost>()
                    .in(SysPost::getPostName, list.stream().map(SysPost::getPostName).collect(Collectors.toSet()))
                );
            }
        };

        EasyExcelHelper.importData(file, SysPost.class, importModeHandleTemplate::handle);

        return importModeHandleTemplate.getResult();
    }

    private List<String> buildExportExcludeFieldNames() {
        return Arrays.asList(
                "createTime",
                "remark"
        );
    }

    private SysPost buildExampleEntity() {
        SysPost entity = new SysPost();
        entity.setPostName("总裁");
        entity.setCode("ceo");
        entity.setStatus(EnableFlag.ENABLED);
        return entity;
    }
}
