package cn.eu.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.eu.common.annotation.Log;
import cn.eu.common.base.controller.EuBaseController;
import cn.eu.common.enums.BusinessType;
import cn.eu.common.model.ResultBody;
import cn.eu.common.utils.EasyExcelHelper;
import cn.eu.system.domain.SysPost;
import cn.eu.system.domain.SysUser;
import cn.eu.system.model.query.SysPostQueryCriteria;
import cn.eu.system.model.query.SysUserQueryCriteria;
import cn.eu.system.service.ISysPostService;
import cn.eu.system.service.ISysUserService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/5/31
 */
@Slf4j
@Tag(name = "系统：岗位管理")
@ApiSupport(author = "cn.zhaoey@gmail.com")
@RequestMapping("/api/system/post")
@RestController
public class SysPostController extends EuBaseController {

    @Autowired
    ISysPostService sysPostService;
    @Autowired
    ISysUserService sysUserService;

    @Log(title = "查看岗位列表", businessType = BusinessType.QUERY, isSaveResponseData = false)
    @Operation(summary = "查询岗位列表")
    @ApiOperationSupport(author = "cn.zhaoey@gmail.com")
    @Parameters({
            @Parameter(name = "criteria", description = "岗位查询条件", required = true),
            @Parameter(name = "pageable", description = "分页参数", required = true)
    })
    @SaCheckPermission(value = {"system:post:list", "system:user:list"}, mode = SaMode.OR)
    @GetMapping("/page")
    public ResultBody page(SysPostQueryCriteria criteria, @PageableDefault(page = 1) Pageable pageable) {
        return ResultBody.ok().data(sysPostService.page(criteria, pageable));
    }

    @Log(title = "新增岗位", businessType = BusinessType.INSERT)
    @SaCheckPermission("system:post:add")
    @PostMapping
    public ResultBody save(@Validated @RequestBody SysPost entity) {
        sysPostService.save(entity);
        return ResultBody.ok();
    }

    @Log(title = "修改岗位", businessType = BusinessType.UPDATE)
    @SaCheckPermission("system:post:edit")
    @PutMapping
    public ResultBody update(@Validated @RequestBody SysPost entity) {
        Assert.notNull(entity.getId(), "id不能为空");
        sysPostService.updateById(entity);
        return ResultBody.ok();
    }

    @Log(title = "删除岗位", businessType = BusinessType.DELETE)
    @SaCheckPermission("system:post:del")
    @DeleteMapping("/batch")
    public ResultBody batchDelete(@RequestBody List<Integer> ids) {
        Assert.notEmpty(ids, "id不能为空");
        Assert.isTrue(sysUserService.countByPostIds(ids) == 0, "岗位中存在用户关联，请先取消关联");
        sysPostService.removeByIds(ids);
        return ResultBody.ok();
    }

    @Log(title = "导出岗位", businessType = BusinessType.EXPORT, isSaveResponseData = false)
    @SaCheckPermission("system:post:export")
    @PostMapping("/export")
    public void export(SysPostQueryCriteria criteria, HttpServletResponse response) throws IOException {
        List<SysPost> list = sysPostService.list(criteria);

        EasyExcelHelper.export(response, list, SysPost.class);
    }

    @Log(title = "下载导入岗位模版", businessType = BusinessType.EXPORT_TEMPLATE, isSaveResponseData = false)
    @SaCheckPermission("system:post:import")
    @PostMapping("/export-template")
    public void exportTemplate(HttpServletResponse response) throws IOException {
        sysPostService.exportTemplate(response);
    }

    @Log(title = "导入岗位", businessType = BusinessType.IMPORT)
    @SaCheckPermission("system:post:import")
    @PostMapping("/import")
    public ResultBody importData(MultipartFile file, @RequestParam(value = "importMode", required = false) Integer importMode) throws IOException {
        return ResultBody.ok()
                .data(sysPostService.importData(file, importMode));
    }

}
