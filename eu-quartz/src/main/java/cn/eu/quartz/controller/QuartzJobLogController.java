package cn.eu.quartz.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.eu.common.annotation.Log;
import cn.eu.common.core.controller.EuBaseController;
import cn.eu.common.enums.BusinessType;
import cn.eu.common.model.ResultBody;
import cn.eu.common.utils.EasyExcelHelper;
import cn.eu.common.utils.MessageUtils;
import cn.eu.quartz.domain.QuartzJobLog;
import cn.eu.quartz.service.IQuartzJobLogService;
import cn.eu.quartz.service.dto.QuartzJobLogQueryCriteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/6/24
 */
@Slf4j
@RestController
@RequestMapping("/api/system/job-log")
public class QuartzJobLogController extends EuBaseController {
    
    @Autowired
    IQuartzJobLogService quartzJobLogService;

    @Log(title = "查看任务日志列表", businessType = BusinessType.QUERY, isSaveResponseData = false)
    @SaCheckPermission("system:job-log:list")
    @GetMapping("/page")
    public ResultBody page(QuartzJobLogQueryCriteria criteria, @PageableDefault(page = 1) Pageable pageable) {
        return ResultBody.ok().data(quartzJobLogService.page(criteria, pageable));
    }

    @Log(title = "删除任务日志", businessType = BusinessType.DELETE)
    @SaCheckPermission("system:job-log:del")
    @DeleteMapping("/batch")
    public ResultBody batchDelete(@RequestBody List<String> ids) {
        Assert.notEmpty(ids, MessageUtils.message("assert.notEmpty", "ids"));
        quartzJobLogService.removeByIds(ids);
        return ResultBody.ok();
    }

    @Log(title = "导出任务日志", businessType = BusinessType.EXPORT)
    @SaCheckPermission("system:job-log:export")
    @PostMapping("/export")
    public void export(QuartzJobLogQueryCriteria criteria, HttpServletResponse response) throws IOException {
        List<QuartzJobLog> list = quartzJobLogService.list(criteria);
        EasyExcelHelper.export(response, list, QuartzJobLog.class);
    }

}
