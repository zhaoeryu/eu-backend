package cn.eu.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.eu.common.annotation.Log;
import cn.eu.common.base.controller.EuBaseController;
import cn.eu.common.enums.BusinessType;
import cn.eu.common.model.ResultBody;
import cn.eu.common.utils.EasyExcelHelper;
import cn.eu.system.domain.SysOperLog;
import cn.eu.system.model.query.SysOperLogQueryCriteria;
import cn.eu.system.service.ISysOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 操作日志 控制器
 * @author zhaoeryu
 * @since 2023/07/11
 */
@RequestMapping("/api/system/sysOperLog")
@RestController
public class SysOperLogController extends EuBaseController {

    @Autowired
    ISysOperLogService sysOperLogService;

    @Log(title = "查看日志列表", businessType = BusinessType.QUERY, isSaveResponseData = false)
    @SaCheckPermission("system:sysOperLog:list")
    @GetMapping("/page")
    public ResultBody page(SysOperLogQueryCriteria criteria, @PageableDefault(page = 1) Pageable pageable) {
        return ResultBody.ok().data(sysOperLogService.page(criteria, pageable));
    }

    @Log(title = "导出操作日志", businessType = BusinessType.EXPORT)
    @SaCheckPermission("system:sysOperLog:export")
    @PostMapping("/export")
    public void export(SysOperLogQueryCriteria criteria, HttpServletResponse response) throws IOException {
        List<SysOperLog> list = sysOperLogService.list(criteria);
        EasyExcelHelper.export(response, list, SysOperLog.class);
    }

}
