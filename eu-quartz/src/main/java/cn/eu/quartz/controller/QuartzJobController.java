package cn.eu.quartz.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.eu.common.annotation.Log;
import cn.eu.common.base.controller.EuBaseController;
import cn.eu.common.enums.BusinessType;
import cn.eu.common.model.ResultBody;
import cn.eu.quartz.domain.QuartzJob;
import cn.eu.quartz.service.IQuartzJobService;
import cn.eu.quartz.service.dto.QuartzJobQueryCriteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/6/24
 */
@Slf4j
@RestController
@RequestMapping("/api/system/job")
public class QuartzJobController extends EuBaseController {
    
    @Autowired
    IQuartzJobService quartzJobService;

    @Log(title = "查看任务列表", businessType = BusinessType.QUERY, isSaveResponseData = false)
    @SaCheckPermission("system:job:list")
    @GetMapping("/page")
    public ResultBody page(QuartzJobQueryCriteria criteria, @PageableDefault(page = 1) Pageable pageable) {
        return ResultBody.ok().data(quartzJobService.page(criteria, pageable));
    }

    @Log(title = "新增任务", businessType = BusinessType.INSERT)
    @SaCheckPermission("system:job:add")
    @PostMapping
    public ResultBody save(@Validated @RequestBody QuartzJob entity) {
        quartzJobService.saveJob(entity);
        return ResultBody.ok();
    }

    @Log(title = "修改任务", businessType = BusinessType.UPDATE)
    @SaCheckPermission("system:job:edit")
    @PutMapping
    public ResultBody update(@Validated @RequestBody QuartzJob entity) {
        Assert.notNull(entity.getId(), "id不能为空");
        quartzJobService.updateJob(entity);
        return ResultBody.ok();
    }

    @Log(title = "删除任务", businessType = BusinessType.DELETE)
    @SaCheckPermission("system:job:del")
    @DeleteMapping("/batch")
    public ResultBody batchDelete(@RequestBody List<String> ids) {
        Assert.notEmpty(ids, "id不能为空");
        quartzJobService.deleteJobs(ids);
        return ResultBody.ok();
    }

    @Log(title = "暂停或恢复任务", businessType = BusinessType.PAUSE_RESUME_JOB)
    @SaCheckPermission("system:job:edit")
    @PostMapping("/pause-or-resume")
    public ResultBody pauseOrResume(@RequestBody QuartzJob entity) {
        Assert.hasText(entity.getId(), "id不能为空");
        Assert.notNull(entity.getStatus(), "状态不能为空");
        quartzJobService.pauseOrResume(entity);
        return ResultBody.ok();
    }

    @Log(title = "执行任务", businessType = BusinessType.EXEC_JOB)
    @SaCheckPermission("system:job:exec")
    @PostMapping("/exec")
    public ResultBody exec(@RequestParam("id") String jobId) {
        Assert.hasText(jobId, "id不能为空");
        quartzJobService.exec(jobId);
        return ResultBody.ok();
    }

}
