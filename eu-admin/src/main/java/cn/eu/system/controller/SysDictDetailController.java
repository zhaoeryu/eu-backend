package cn.eu.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.eu.common.annotation.Log;
import cn.eu.common.base.controller.EuBaseController;
import cn.eu.common.enums.BusinessType;
import cn.eu.common.model.ResultBody;
import cn.eu.common.utils.EasyExcelHelper;
import cn.eu.system.domain.SysDict;
import cn.eu.system.domain.SysDictDetail;
import cn.eu.system.model.query.SysDictDetailQueryCriteria;
import cn.eu.system.model.query.SysDictQueryCriteria;
import cn.eu.system.service.ISysDictDetailService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
@RequestMapping("/api/system/dict-detail")
@RestController
public class SysDictDetailController extends EuBaseController {

    @Autowired
    ISysDictDetailService sysDictDetailService;

    @Log(title = "查看字典详情列表", businessType = BusinessType.QUERY, isSaveResponseData = false)
    @SaCheckPermission("system:dict-detail:list")
    @GetMapping("/page")
    public ResultBody page(SysDictDetailQueryCriteria criteria, @PageableDefault(page = 1) Pageable pageable) {
        return ResultBody.ok().data(sysDictDetailService.page(criteria, pageable));
    }

    @Log(title = "新增字典详情", businessType = BusinessType.INSERT)
    @SaCheckPermission("system:dict-detail:add")
    @PostMapping
    public ResultBody save(@Validated @RequestBody SysDictDetail entity) {
        sysDictDetailService.save(entity);
        return ResultBody.ok();
    }

    @Log(title = "修改字典详情", businessType = BusinessType.UPDATE)
    @SaCheckPermission("system:dict-detail:edit")
    @PutMapping
    public ResultBody update(@Validated @RequestBody SysDictDetail entity) {
        Assert.notNull(entity.getId(), "id不能为空");
        sysDictDetailService.updateById(entity);
        return ResultBody.ok();
    }

    @Log(title = "删除字典详情", businessType = BusinessType.DELETE)
    @SaCheckPermission("system:dict-detail:del")
    @DeleteMapping("/batch")
    public ResultBody batchDelete(@RequestBody List<Integer> ids) {
        Assert.notEmpty(ids, "id不能为空");
        sysDictDetailService.removeByIds(ids);
        return ResultBody.ok();
    }

    @GetMapping("/listByDictKey")
    public ResultBody list(@RequestParam("dictKey") String dictKey) {
        List<SysDictDetail> res = sysDictDetailService.list(new LambdaQueryWrapper<SysDictDetail>()
                .eq(SysDictDetail::getDictKey, dictKey)
                .orderByAsc(SysDictDetail::getSortNum)
        );
        return ResultBody.ok().data(res);
    }

    @Log(title = "导出字典详情", businessType = BusinessType.EXPORT, isSaveResponseData = false)
    @SaCheckPermission("system:dict-detail:export")
    @PostMapping("/export")
    public void export(SysDictDetailQueryCriteria criteria, HttpServletResponse response) throws IOException {
        List<SysDictDetail> list = sysDictDetailService.list(criteria);

        EasyExcelHelper.export(response, list, SysDictDetail.class);
    }

    @Log(title = "下载导入字典详情模版", businessType = BusinessType.EXPORT_TEMPLATE, isSaveResponseData = false)
    @SaCheckPermission("system:dict-detail:import")
    @PostMapping("/export-template")
    public void exportTemplate(HttpServletResponse response) throws IOException {
        sysDictDetailService.exportTemplate(response);
    }

    @Log(title = "导入字典详情", businessType = BusinessType.IMPORT)
    @SaCheckPermission("system:dict-detail:import")
    @PostMapping("/import")
    public ResultBody importData(MultipartFile file,
         @RequestParam(value = "importMode") Integer importMode,
         @RequestParam(value = "dictId") Integer dictId
    ) throws IOException {
        return ResultBody.ok()
                .data(sysDictDetailService.importData(file, importMode, dictId));
    }
}
