package cn.eu.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统服务异常
 *
 * @author Eu.z
 * @since 2024/11/24
 */
public class ServerException extends EuException {

    public ServerException() {
        super(EuErrorCode.SERVER_ERROR);
    }

    public ServerException(String message) {
        super(EuErrorCode.SERVER_ERROR.getCode(), message);
    }

}
