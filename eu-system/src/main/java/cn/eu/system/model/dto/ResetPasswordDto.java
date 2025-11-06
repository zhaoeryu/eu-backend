package cn.eu.system.model.dto;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * @author zhaoeryu
 * @since 2023/8/1
 */
@Data
public class ResetPasswordDto {

    @NotBlank(message = "用户id不能为空")
    private String id;
    @NotBlank(message = "密码不能为空")
    private String password;

}
