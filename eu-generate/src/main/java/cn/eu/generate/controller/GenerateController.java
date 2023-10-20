package cn.eu.generate.controller;

import cn.eu.common.annotation.Log;
import cn.eu.common.enums.BusinessType;
import cn.eu.common.model.OptionItem;
import cn.eu.common.model.PageResult;
import cn.eu.common.model.ResultBody;
import cn.eu.common.utils.Query;
import cn.eu.generate.domain.GenTable;
import cn.eu.generate.domain.GenTableColumn;
import cn.eu.generate.enums.FormType;
import cn.eu.generate.model.dto.GenerateTemplateDto;
import cn.eu.generate.model.vo.TableInfoVo;
import cn.eu.generate.service.IGenTableService;
import cn.eu.generate.model.query.GenTableQueryCriteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhaoeryu
 * @since 2023/6/27
 */
@Slf4j
@RestController
@RequestMapping("/gen")
public class GenerateController {

    @Autowired
    IGenTableService genTableService;

    @Log(title = "查看代码生成列表", businessType = BusinessType.QUERY, isSaveResponseData = false)
    @GetMapping("/page")
    public ResultBody page(GenTableQueryCriteria criteria, @PageableDefault(page = 1) Pageable pageable) {
        PageResult<GenTable> result = genTableService.listTables(criteria, pageable);
        return ResultBody.ok().data(result);
    }

    @Log(title = "预览代码", businessType = BusinessType.QUERY, isSaveResponseData = false)
    @GetMapping("/preview")
    public ResultBody preview(@RequestParam("tableName") String tableName) {
        List<GenerateTemplateDto> list = genTableService.preview(tableName);
        return ResultBody.ok().data(list);
    }

    @GetMapping("/tableInfo")
    public ResultBody columns(@RequestParam("tableName") String tableName) {
        TableInfoVo tableInfo = genTableService.getTableInfo(tableName);
        return ResultBody.ok().data(tableInfo);
    }

    @Log(title = "保存代码生成表配置", businessType = BusinessType.OTHER)
    @PostMapping("/table")
    public ResultBody saveTable(@RequestBody @Validated GenTable entity) {
        genTableService.saveTable(entity);
        return ResultBody.ok();
    }
    @Log(title = "保存代码生成列配置", businessType = BusinessType.OTHER)
    @PostMapping("/columns")
    public ResultBody saveColumns(@RequestBody @Validated List<GenTableColumn> entityList) {
        genTableService.saveColumns(entityList);
        return ResultBody.ok();
    }

    @Log(title = "同步表配置", businessType = BusinessType.OTHER)
    @PostMapping("/sync")
    public ResultBody syncTable(@RequestParam("tableName") String tableName) {
        genTableService.sync(tableName);
        return ResultBody.ok();
    }

    @Log(title = "生成代码", businessType = BusinessType.OTHER, isSaveResponseData = false)
    @PostMapping("/gen")
    public void gen(@RequestParam("tableName") String tableName, HttpServletResponse response) throws IOException {
        genTableService.generator(tableName, response);
    }

    /**
     * 获取所有的查询方式
     * @return /
     */
    @GetMapping("/queryType")
    public ResultBody queryType() {
        List<String> list = Arrays.stream(Query.Type.values()).map(Query.Type::name).collect(Collectors.toList());
        return ResultBody.ok().data(list);
    }

    /**
     * 获取所有的表单类型
     */
    @GetMapping("/formType")
    public ResultBody formType() {
        List<OptionItem> list = Arrays.stream(FormType.values())
                .map(item -> new OptionItem(item.getLabel(), item.getValue()))
                .collect(Collectors.toList());
        return ResultBody.ok().data(list);
    }
}
