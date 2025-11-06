package cn.eu.system.model.dto;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author zhaoeryu
 * @since 2023/8/1
 */
@Data
public class UpdateUserStatusDto {

    @NotBlank(message = "用户id不能为空")
    private String id;
    @NotNull(message = "状态不能为空")
    private Integer status;

}
