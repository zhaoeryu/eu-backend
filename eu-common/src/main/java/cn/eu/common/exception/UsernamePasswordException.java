package cn.eu.common.exception;

/**
 * 用户名密码错误
 *
 * @author Eu.z
 * @since 2024/11/24
 */
public class UsernamePasswordException extends EuException {

    public UsernamePasswordException() {
        super(EuErrorCode.SYS_LOGIN_USERNAME_OR_PASSWORD_ERROR);
    }

    public UsernamePasswordException(String message) {
        super(EuErrorCode.SYS_LOGIN_USERNAME_OR_PASSWORD_ERROR.getCode(), message);
    }

}
