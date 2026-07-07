package cn.eu.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.eu.common.annotation.Log;
import cn.eu.common.core.controller.EuBaseController;
import cn.eu.common.enums.BusinessType;
import cn.eu.common.model.ResultBody;
import cn.eu.common.utils.EasyExcelHelper;
import cn.eu.common.utils.MessageUtils;
import cn.eu.system.domain.SysConfig;
import cn.eu.system.model.query.SysConfigQueryCriteria;
import cn.eu.system.service.ISysConfigService;
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

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author zhaoeryu
 * @since 2026/7/6
 */
@Slf4j
@Tag(name = "系统：参数配置")
@ApiSupport(author = "cn.zhaoey@gmail.com")
@RequestMapping("/api/system/config")
@RestController
public class SysConfigController extends EuBaseController {

    @Autowired
    ISysConfigService sysConfigService;

    @Log(title = "查看参数配置列表", businessType = BusinessType.QUERY, isSaveResponseData = false)
    @Operation(summary = "查询参数配置列表")
    @ApiOperationSupport(author = "cn.zhaoey@gmail.com")
    @Parameters({
            @Parameter(name = "criteria", description = "参数配置查询条件", required = true),
            @Parameter(name = "pageable", description = "分页参数", required = true)
    })
    @SaCheckPermission("system:config:list")
    @GetMapping("/page")
    public ResultBody page(SysConfigQueryCriteria criteria, @PageableDefault(page = 1) Pageable pageable) {
        return ResultBody.ok().data(sysConfigService.page(criteria, pageable));
    }

    @Log(title = "查询参数配置", businessType = BusinessType.QUERY, isSaveResponseData = false)
    @Operation(summary = "根据参数键名查询参数值")
    @ApiOperationSupport(author = "cn.zhaoey@gmail.com")
    @SaCheckPermission("system:config:query")
    @GetMapping("/configKey/{configKey}")
    public ResultBody getConfigKey(@PathVariable String configKey) {
        return ResultBody.ok().data(sysConfigService.selectConfigByKey(configKey));
    }

    @Log(title = "新增参数配置", businessType = BusinessType.INSERT)
    @SaCheckPermission("system:config:add")
    @PostMapping
    public ResultBody save(@Validated @RequestBody SysConfig entity) {
        Assert.isTrue(sysConfigService.checkConfigKeyUnique(entity), MessageUtils.message("valid.SysConfig.configKey.unique"));
        sysConfigService.save(entity);
        return ResultBody.ok();
    }

    @Log(title = "修改参数配置", businessType = BusinessType.UPDATE)
    @SaCheckPermission("system:config:edit")
    @PutMapping
    public ResultBody update(@Validated @RequestBody SysConfig entity) {
        Assert.notNull(entity.getConfigId(), MessageUtils.message("assert.notNull", "configId"));
        Assert.isTrue(sysConfigService.checkConfigKeyUnique(entity), MessageUtils.message("valid.SysConfig.configKey.unique"));
        sysConfigService.updateById(entity);
        return ResultBody.ok();
    }

    @Log(title = "删除参数配置", businessType = BusinessType.DELETE)
    @SaCheckPermission("system:config:del")
    @DeleteMapping("/batch")
    public ResultBody batchDelete(@RequestBody List<String> ids) {
        Assert.notEmpty(ids, MessageUtils.message("assert.notNull", "ids"));
        sysConfigService.removeByIds(ids);
        return ResultBody.ok();
    }

    @Log(title = "导出参数配置", businessType = BusinessType.EXPORT, isSaveResponseData = false)
    @SaCheckPermission("system:config:export")
    @PostMapping("/export")
    public void export(SysConfigQueryCriteria criteria, HttpServletResponse response) throws IOException {
        List<SysConfig> list = sysConfigService.list(criteria);
        EasyExcelHelper.export(response, list, SysConfig.class);
    }

}
