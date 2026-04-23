package cn.eu.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 框架异常
 *
 * @author Eu.z
 * @since 2024/11/24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class EuException extends RuntimeException {

    private final int code;
    private final String message;
    // 可以加入原始异常信息，用于日志追溯
    private Throwable cause;

    public EuException(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public EuException(ErrorCode errorCode, String... params) {
        this.code = errorCode.getCode();
        // 如果errorCode的消息模板支持参数替换，例如 "用户 {0} 不存在"，这里可以进行处理
        // 这里简化处理，直接使用原消息或拼接消息
        if (params != null && params.length > 0) {
            this.message = String.format(errorCode.getMessage(), (Object[]) params);
        } else {
            this.message = errorCode.getMessage();
        }
    }

    public EuException(ErrorCode errorCode, Throwable cause) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.cause = cause;
    }

    public EuException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
