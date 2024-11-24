package cn.eu.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.eu.common.annotation.Log;
import cn.eu.common.base.controller.EuBaseController;
import cn.eu.common.enums.BusinessType;
import cn.eu.common.model.ResultBody;
import cn.eu.common.utils.MessageUtils;
import cn.eu.system.domain.SysMenu;
import cn.eu.system.model.query.SysMenuQueryCriteria;
import cn.eu.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/5/31
 */
@RequestMapping("/api/system/menu")
@RestController
public class SysMenuController extends EuBaseController {

    @Autowired
    ISysMenuService sysMenuService;

    @GetMapping("/routers")
    public ResultBody routers() {
        return ResultBody.ok()
                .data(sysMenuService.getMenusByUserId(StpUtil.getLoginIdAsString()));
    }

    @Log(title = "查看菜单列表", businessType = BusinessType.QUERY, isSaveResponseData = false)
    @SaCheckPermission("system:menu:list")
    @GetMapping("/list")
    public ResultBody page(SysMenuQueryCriteria criteria) {
        return ResultBody.ok().data(sysMenuService.list(criteria));
    }

    @Log(title = "新增菜单", businessType = BusinessType.INSERT)
    @SaCheckPermission("system:menu:add")
    @PostMapping
    public ResultBody save(@Validated @RequestBody SysMenu entity) {
        sysMenuService.save(entity);
        return ResultBody.ok();
    }

    @Log(title = "修改菜单", businessType = BusinessType.UPDATE)
    @SaCheckPermission("system:menu:edit")
    @PutMapping
    public ResultBody update(@Validated @RequestBody SysMenu entity) {
        Assert.notNull(entity.getId(), MessageUtils.message("assert.notNull", "id"));
        sysMenuService.updateById(entity);
        return ResultBody.ok();
    }

    @Log(title = "删除菜单", businessType = BusinessType.DELETE)
    @SaCheckPermission("system:menu:del")
    @DeleteMapping("/batch")
    public ResultBody batchDelete(@RequestBody List<Integer> ids) {
        Assert.notEmpty(ids, MessageUtils.message("assert.notEmpty", "ids"));
        sysMenuService.removeByIds(ids);
        return ResultBody.ok();
    }

}
