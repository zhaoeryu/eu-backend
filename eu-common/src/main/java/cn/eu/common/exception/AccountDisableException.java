package cn.eu.common.exception;

/**
 * 账户禁用
 *
 * @author Eu.z
 * @since 2024/11/24
 */
public class AccountDisableException extends EuException {

    public AccountDisableException() {
        super(EuErrorCode.SYS_LOGIN_ACCOUNT_DISABLED);
    }

    public AccountDisableException(String message) {
        super(EuErrorCode.SYS_LOGIN_ACCOUNT_DISABLED.getCode(), message);
    }

}
