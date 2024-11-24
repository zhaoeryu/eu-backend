package cn.eu.system.model.pojo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author zhaoeryu
 * @since 2023/6/6
 */
@Data
public class UpdatePasswordBody {

    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;
    @NotBlank(message = "新密码不能为空")
    private String newPassword;

}
