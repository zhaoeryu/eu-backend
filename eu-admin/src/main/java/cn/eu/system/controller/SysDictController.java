package cn.eu.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.eu.common.annotation.Log;
import cn.eu.common.base.controller.EuBaseController;
import cn.eu.common.enums.BusinessType;
import cn.eu.common.model.ResultBody;
import cn.eu.common.utils.EasyExcelHelper;
import cn.eu.common.utils.MessageUtils;
import cn.eu.system.domain.SysDict;
import cn.eu.system.domain.SysRole;
import cn.eu.system.model.query.SysDictQueryCriteria;
import cn.eu.system.model.query.SysRoleQueryCriteria;
import cn.eu.system.service.ISysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/5/31
 */
@RequestMapping("/api/system/dict")
@RestController
public class SysDictController extends EuBaseController {

    @Autowired
    ISysDictService sysDictService;

    @Log(title = "查看字典列表", businessType = BusinessType.QUERY, isSaveResponseData = false)
    @SaCheckPermission("system:dict:list")
    @GetMapping("/page")
    public ResultBody page(SysDictQueryCriteria criteria, @PageableDefault(page = 1) Pageable pageable) {
        return ResultBody.ok().data(sysDictService.page(criteria, pageable));
    }

    @Log(title = "新增字典", businessType = BusinessType.INSERT)
    @SaCheckPermission("system:dict:add")
    @PostMapping
    public ResultBody save(@Validated @RequestBody SysDict entity) {
        sysDictService.save(entity);
        return ResultBody.ok();
    }

    @Log(title = "修改字典", businessType = BusinessType.UPDATE)
    @SaCheckPermission("system:dict:edit")
    @PutMapping
    public ResultBody update(@Validated @RequestBody SysDict entity) {
        Assert.notNull(entity.getId(), MessageUtils.message("assert.notNull", "id"));
        sysDictService.updateDict(entity);
        return ResultBody.ok();
    }

    @Log(title = "删除字典", businessType = BusinessType.DELETE)
    @SaCheckPermission("system:dict:del")
    @DeleteMapping("/batch")
    public ResultBody batchDelete(@RequestBody List<Integer> ids) {
        Assert.notEmpty(ids, MessageUtils.message("assert.notEmpty", "ids"));
        sysDictService.removeDictByIds(ids);
        return ResultBody.ok();
    }

    @Log(title = "导出字典", businessType = BusinessType.EXPORT, isSaveResponseData = false)
    @SaCheckPermission("system:dict:export")
    @PostMapping("/export")
    public void export(SysDictQueryCriteria criteria, HttpServletResponse response) throws IOException {
        List<SysDict> list = sysDictService.list(criteria);

        EasyExcelHelper.export(response, list, SysDict.class);
    }

    @Log(title = "下载导入字典模版", businessType = BusinessType.EXPORT_TEMPLATE, isSaveResponseData = false)
    @SaCheckPermission("system:dict:import")
    @PostMapping("/export-template")
    public void exportTemplate(HttpServletResponse response) throws IOException {
        sysDictService.exportTemplate(response);
    }

    @Log(title = "导入字典", businessType = BusinessType.IMPORT)
    @SaCheckPermission("system:dict:import")
    @PostMapping("/import")
    public ResultBody importData(MultipartFile file, @RequestParam(value = "importMode", required = false) Integer importMode) throws IOException {
        return ResultBody.ok()
                .data(sysDictService.importData(file, importMode));
    }
}
