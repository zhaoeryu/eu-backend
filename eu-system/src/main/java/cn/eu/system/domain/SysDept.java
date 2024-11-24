package cn.eu.system.domain;

import cn.eu.common.base.domain.BaseEntity;
import cn.eu.common.enums.DeptStatus;
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
@TableName("sys_dept")
public class SysDept extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ExcelProperty("部门ID")
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 部门名称
     */
    @ExcelProperty("部门名称")
    @NotBlank(message = "{valid.SysDept.deptName.notBlank}")
    private String deptName;
    /**
     * 状态
     * @see DeptStatus#getValue()
     */
    private Integer status;
    /**
     * 显示顺序
     */
    private Integer sortNum;
    /**
     * 父部门id
     */
    private Integer parentId;
    /**
     * 祖级列表
     */
    private String parentIds;
}
