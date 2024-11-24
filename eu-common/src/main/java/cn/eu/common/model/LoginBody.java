package cn.eu.common.model;

import lombok.Data;
import javax.validation.constraints.NotBlank;

/**
 * @author zhaoeryu
 * @since 2023/6/3
 */
@Data
public class LoginBody {

    @NotBlank(message = "{valid.LoginBody.username.notBlank}")
    private String username;
    @NotBlank(message = "{valid.LoginBody.password.notBlank}")
    private String password;
    /** 缓存验证码的RedisKey */
    @NotBlank(message = "{valid.LoginBody.uuid.notBlank}")
    private String uuid;
    @NotBlank(message = "{valid.LoginBody.verifyCode.notBlank}")
    private String verifyCode;

}
