package cn.eu.system.domain;

import cn.eu.common.base.domain.BaseEntity;
import cn.eu.common.enums.PostStatus;
import cn.eu.system.easyexcel.converter.SysPostStatusConverter;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * @author zhaoeryu
 * @since 2023/6/3
 */
@Schema(description = "岗位 - 实体类")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_post")
public class SysPost extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ExcelIgnore
    @Schema(description = "岗位ID")
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 岗位名称
     */
    @ExcelProperty(value = "岗位名称")
    @Schema(description = "岗位名称")
    @NotBlank(message = "岗位名称不能为空")
    private String postName;
    /**
     * 岗位编码
     */
    @ExcelProperty(value = "岗位编码")
    @Schema(description = "岗位编码")
    @NotBlank(message = "岗位编码不能为空")
    private String code;
    /**
     * 状态
     * @see PostStatus#getValue()
     */
    @ExcelProperty(value = "状态", converter = SysPostStatusConverter.class)
    @Schema(description = "状态")
    private Integer status;
}
