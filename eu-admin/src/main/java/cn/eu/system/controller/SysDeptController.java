package cn.eu.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.eu.common.annotation.Log;
import cn.eu.common.base.controller.EuBaseController;
import cn.eu.common.enums.BusinessType;
import cn.eu.common.model.ResultBody;
import cn.eu.system.domain.SysDept;
import cn.eu.system.model.query.SysDeptQueryCriteria;
import cn.eu.system.service.ISysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/5/31
 */
@RequestMapping("/api/system/dept")
@RestController
public class SysDeptController extends EuBaseController {

    @Autowired
    ISysDeptService sysDeptService;

    @Log(title = "查看部门列表", businessType = BusinessType.QUERY, isSaveResponseData = false)
    @SaCheckPermission(value = {"system:dept:list", "system:user:list"}, mode = SaMode.OR)
    @GetMapping("/list")
    public ResultBody list(SysDeptQueryCriteria criteria) {
        return ResultBody.ok().data(sysDeptService.list(criteria));
    }

    @Log(title = "新增部门", businessType = BusinessType.INSERT)
    @SaCheckPermission("system:dept:add")
    @PostMapping
    public ResultBody save(@Validated @RequestBody SysDept entity) {
        sysDeptService.save(entity);
        return ResultBody.ok();
    }

    @Log(title = "修改部门", businessType = BusinessType.UPDATE)
    @SaCheckPermission("system:dept:edit")
    @PutMapping
    public ResultBody update(@Validated @RequestBody SysDept entity) {
        Assert.notNull(entity.getId(), "id不能为空");
        sysDeptService.updateById(entity);
        return ResultBody.ok();
    }

    @Log(title = "删除部门", businessType = BusinessType.DELETE)
    @SaCheckPermission("system:dept:del")
    @DeleteMapping("/batch")
    public ResultBody batchDelete(@RequestBody List<Integer> ids) {
        Assert.notEmpty(ids, "id不能为空");
        sysDeptService.checkCanDelete(ids);
        sysDeptService.removeByIds(ids);
        return ResultBody.ok();
    }

}
