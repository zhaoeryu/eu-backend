package cn.eu.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.eu.common.annotation.Log;
import cn.eu.common.base.controller.EuBaseController;
import cn.eu.common.enums.BusinessType;
import cn.eu.common.model.ResultBody;
import cn.eu.common.utils.EasyExcelHelper;
import cn.eu.common.utils.LoginUtil;
import cn.eu.common.utils.MessageUtils;
import cn.eu.system.domain.SysNotice;
import cn.eu.system.model.query.SysNoticeQueryCriteria;
import cn.eu.system.service.ISysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 通知公告 控制器
 * @author Eu.z
 * @since 2023/08/30
 */
@RequestMapping("/api/system/sysNotice")
@RestController
public class SysNoticeController extends EuBaseController {

    @Autowired
    ISysNoticeService sysNoticeService;

    @Log(title = "查看通知列表", businessType = BusinessType.QUERY, isSaveResponseData = false)
    @GetMapping("/page")
    public ResultBody page(SysNoticeQueryCriteria criteria, @PageableDefault(page = 1) Pageable pageable) {
        return ResultBody.ok().data(sysNoticeService.page(criteria, pageable));
    }
    
    @SaCheckPermission("system:sysNotice:query")
    @GetMapping
    public ResultBody getById(@RequestParam("id") String id) {
        return ResultBody.ok().data(sysNoticeService.getById(id));
    }
    
    @Log(title = "新增通知公告", businessType = BusinessType.INSERT)
    @SaCheckPermission("system:sysNotice:add")
    @PostMapping
    public ResultBody save(@Validated @RequestBody SysNotice entity) {
        entity.setPublisher(LoginUtil.getLoginUser().getNickname());
        sysNoticeService.save(entity);
        return ResultBody.ok();
    }
    
    @Log(title = "修改通知公告", businessType = BusinessType.UPDATE)
    @SaCheckPermission("system:sysNotice:edit")
    @PutMapping
    public ResultBody update(@Validated @RequestBody SysNotice entity) {
        Assert.notNull(entity.getId(), MessageUtils.message("assert.notNull", "id"));
        sysNoticeService.updateById(entity);
        return ResultBody.ok();
    }
    
    @Log(title = "删除通知公告", businessType = BusinessType.DELETE)
    @SaCheckPermission("system:sysNotice:del")
    @DeleteMapping
    public ResultBody delete(@RequestParam("id") String id) {
        sysNoticeService.removeById(id);
        return ResultBody.ok();
    }
    
    @Log(title = "批量删除通知公告", businessType = BusinessType.DELETE)
    @SaCheckPermission("system:sysNotice:del")
    @DeleteMapping("/batch")
    public ResultBody batchDelete(@RequestBody List<String> ids) {
        Assert.notEmpty(ids, MessageUtils.message("assert.notEmpty", "ids"));
        sysNoticeService.removeByIds(ids);
        return ResultBody.ok();
    }

    @Log(title = "导出通知公告", businessType = BusinessType.EXPORT)
    @SaCheckPermission("system:sysNotice:export")
    @PostMapping("/export")
    public void export(SysNoticeQueryCriteria criteria, HttpServletResponse response) throws IOException {
        List<SysNotice> list = sysNoticeService.list(criteria);

        EasyExcelHelper.export(response, list, SysNotice.class);
    }

}
