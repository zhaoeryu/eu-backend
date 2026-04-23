package cn.eu.common.exception;

/**
 * 账户锁定异常
 *
 * @author Eu.z
 * @since 2024/11/24
 */
public class AccountLockException extends EuException {

    public AccountLockException() {
        super(EuErrorCode.SYS_LOGIN_LOCK);
    }

    public AccountLockException(String message) {
        super(EuErrorCode.SYS_LOGIN_LOCK.getCode(), message);
    }

}
