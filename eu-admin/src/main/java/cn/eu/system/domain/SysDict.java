package cn.eu.system.domain;

import cn.eu.common.base.domain.BaseEntity;
import cn.eu.common.enums.SysDictStatus;
import cn.eu.system.easyexcel.converter.SysDictStatusConverter;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

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
    @NotBlank(message = "字典Key不能为空")
    private String dictKey;
    /**
     * 状态
     * @see SysDictStatus#getValue()
     */
    @ExcelProperty(value = "状态", converter = SysDictStatusConverter.class)
    private Integer status;

}
