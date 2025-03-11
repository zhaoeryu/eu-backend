package cn.eu.common.core.domain;

import cn.eu.common.annotation.Translation;
import cn.eu.common.constants.TransConstant;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhaoeryu
 * @since 2023/5/31
 */
@Data
public abstract class BaseEntity implements Serializable {

    @Translation(type = TransConstant.USER_ID_TO_NAME, ref = "createByNickname")
    @Schema(description = "创建者")
    @ExcelIgnore
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Translation(type = TransConstant.USER_ID_TO_NAME, ref = "updateByNickname")
    @Schema(description = "更新者")
    @ExcelIgnore
    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;

    @Schema(description = "更新时间")
    @ExcelIgnore
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String remark;

    @Schema(description = "删除标志(0:正常,1:删除)")
    @ExcelIgnore
    @TableLogic(value = "0", delval = "1")
    private Integer delFlag;

}
