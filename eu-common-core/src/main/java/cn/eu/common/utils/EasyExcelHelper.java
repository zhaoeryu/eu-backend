package cn.eu.common.utils;

import cn.eu.common.model.ResultBody;
import cn.eu.common.utils.easyexcel.*;
import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

/**
 * EasyExcel使用帮助类
 * @author zhaoeryu
 * @since 2023/6/10
 */
@Slf4j
public class EasyExcelHelper {

    /**
     * 导出Excel
     * <code>
     *     例子：EasyExcelHelper.export(response, list, SysUser.class);
     * </code>
     * @param response HttpServletResponse
     * @param dataList 要导出的数据
     * @param clazz 要导出数据的实体类
     * @throws IOException io异常
     */
    public static <T> void export(HttpServletResponse response, List<T> dataList, Class<T> clazz) throws IOException {
        export(response, dataList, clazz, null);
    }
    public static <T> void export(HttpServletResponse response, List<T> dataList, Class<T> clazz, List<String> excludeColumnFieldNames) throws IOException {
        // 构建sheet
        ExcelWriterSheetBuilder builder = EasyExcel.writerSheet("Sheet1")
                .head(clazz);

        if (CollUtil.isNotEmpty(excludeColumnFieldNames)) {
            builder.excludeColumnFieldNames(excludeColumnFieldNames);
        }

        WriteSheet writeSheet = builder.build();

        // 填写数据并写入到response
        EasyExcelWriteSheet sheet = EasyExcelWriteSheet.of(writeSheet, dataList)
                .registerStandardWriteHandler();
        export(response, sheet);
    }

    /**
     * 导出Excel
     * <code>
     *     EasyExcelHelper.export(response, list, Arrays.asList(
     *             EasyExcelCellItem.of("ID" , SysUser::getId),
     *             EasyExcelCellItem.of("登录名" , SysUser::getUsername),
     *             EasyExcelCellItem.of("用户昵称" , SysUser::getNickname),
     *             EasyExcelCellItem.of("最后一次活跃时间" , SysUser::getLastActiveTime)
     *         ));
     * </code>
     *
     * @param response HttpServletResponse
     * @param dataList 要导出的数据
     * @param easyExcelCellItems 导出的数据配置
     * @throws IOException io异常
     */
    public static <T> void export(HttpServletResponse response, List<T> dataList, List<EasyExcelCellItem<T>> easyExcelCellItems) throws IOException {
        // 构建Excel头部和内容
        EasyExcelHeadContent headContent = EasyExcelUtil.buildHeadContent(dataList, easyExcelCellItems);

        // 构建sheet
        WriteSheet writeSheet = EasyExcel.writerSheet("Sheet1")
                .head(headContent.getHeadList())
                .build();

        // 填写数据并写入到response
        EasyExcelWriteSheet sheet = EasyExcelWriteSheet.of(writeSheet, headContent.getContentList())
                .registerStandardWriteHandler();
        export(response, sheet);
    }

    public static void export(HttpServletResponse response, EasyExcelWriteSheet... sheetItems) throws IOException {
        Assert.notEmpty(sheetItems, "最少要写入一个sheet");
        try {
            ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream())
                    .autoCloseStream(false)
                    .build();

            for (EasyExcelWriteSheet sheetItem : sheetItems) {
                // 数据写入sheet
                excelWriter.write(sheetItem.getDataList(), sheetItem.getWriteSheet());
            }

            excelWriter.finish();
        } catch (IOException e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(ResultBody.failed()
                    .msg("下载文件失败")
                    .toJsonString());
        }
    }

    public static <T> void importData(MultipartFile file, Class<T> clazz, Consumer<List<T>> saveConsumer) throws IOException {
        importData(file, clazz, saveConsumer, true);
    }

    public static <T> void importData(MultipartFile file, Class<T> clazz, Consumer<List<T>> saveConsumer, boolean isPrintLog) throws IOException {
        importData(file, clazz, saveConsumer, isPrintLog, 100);
    }

    /**
     * 导出数据
     * @param file MultipartFile
     * @param clazz 实体类类型
     * @param saveConsumer 保存数据的回掉
     * @param isPrintLog 是否打印日志
     * @param batchCount 每隔(batchCount)条存储数据库，然后清理list ，方便内存回收
     * @throws IOException io异常
     */
    public static <T> void importData(MultipartFile file, Class<T> clazz, Consumer<List<T>> saveConsumer, boolean isPrintLog, int batchCount) throws IOException {
        EasyExcel.read(file.getInputStream(), clazz, new ReadListener<T>() {
            /**
             * 缓存的数据
             */
            private List<T> cachedDataList = ListUtils.newArrayListWithExpectedSize(batchCount);

            @Override
            public void invoke(T entity, AnalysisContext analysisContext) {
                if (isPrintLog) {
                    log.debug("解析到一条数据:{}", JSONObject.toJSONString(entity));
                }
                cachedDataList.add(entity);
                // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
                if (cachedDataList.size() >= batchCount) {
                    saveData();
                    // 存储完成清理 list
                    cachedDataList = ListUtils.newArrayListWithExpectedSize(batchCount);
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                // 这里也要保存数据，确保最后遗留的数据也存储到数据库
                saveData();
                if (isPrintLog) {
                    log.info("所有数据解析完成！");
                }
            }

            private void saveData() {
                if (CollUtil.isEmpty(this.cachedDataList)) {
                    return;
                }
                saveConsumer.accept(this.cachedDataList);
            }
        }).sheet().doRead();
    }

}
