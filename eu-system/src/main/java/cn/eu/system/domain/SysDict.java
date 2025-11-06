package cn.eu.system.domain;

import cn.eu.common.core.domain.BaseEntity;
import cn.eu.common.enums.EnableFlag;
import cn.eu.common.utils.easyexcel.EasyExcelEnumConverter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;

/**
 * @author zhaoeryu
 * @since 2023/6/3
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dict")
public class SysDict extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ExcelProperty("字典Id")
    @TableId(type = IdType.AUTO)
    private Integer id;
    /** 字典Key */
    @ExcelProperty("字典Key")
    @NotBlank(message = "{valid.SysDict.dictKey.notBlank}")
    private String dictKey;
    @ExcelProperty(value = "状态", converter = EasyExcelEnumConverter.class)
    private EnableFlag status;

}
