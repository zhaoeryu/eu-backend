package cn.eu.system.domain;

import cn.eu.common.core.domain.BaseEntity;
import cn.eu.common.enums.SysDictDetailStatus;
import cn.eu.system.easyexcel.converter.SysDictDetailStatusConverter;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author zhaoeryu
 * @since 2023/6/3
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dict_detail")
public class SysDictDetail extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ExcelIgnore
    @TableId(type = IdType.AUTO)
    private Integer id;
    /** 字典ID */
    @NotNull(message = "{valid.SysDictDetail.pid.notNull}")
    @ExcelProperty("字典ID")
    private Integer pid;
    /** 字典Key */
    @ExcelProperty("字典Key")
    @NotBlank(message = "{valid.SysDictDetail.dictKey.notBlank}")
    private String dictKey;
    /** 字典标签 */
    @ExcelProperty("字典详情标签")
    @NotBlank(message = "{valid.SysDictDetail.dictLabel.notBlank}")
    private String dictLabel;
    /** 字典值 */
    @ExcelProperty("字典详情值")
    @NotBlank(message = "{valid.SysDictDetail.dictValue.notBlank}")
    private String dictValue;
    /**
     * 状态
     * @see SysDictDetailStatus#getValue()
     */
    @ExcelProperty(value = "状态", converter = SysDictDetailStatusConverter.class)
    private Integer status;
    /** 排序 */
    @ExcelProperty("排序")
    private Integer sortNum;

}