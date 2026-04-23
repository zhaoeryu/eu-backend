package cn.eu.common.exception;

/**
 * 账户已被删除
 *
 * @author Eu.z
 * @since 2024/11/24
 */
public class AccountDeleteException extends EuException {

    public AccountDeleteException() {
        super(EuErrorCode.SYS_LOGIN_ACCOUNT_DELETED);
    }

    public AccountDeleteException(String message) {
        super(EuErrorCode.SYS_LOGIN_ACCOUNT_DELETED.getCode(), message);
    }

}
