package cn.eu.common.exception;

/**
 * 业务异常
 *
 * @author Eu.z
 * @since 2024/11/24
 */
public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }
}
