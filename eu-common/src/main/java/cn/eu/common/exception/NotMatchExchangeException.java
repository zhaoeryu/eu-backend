package cn.eu.common.exception;

/**
 * 没有找到匹配的exchange异常
 * @author zhaoeryu
 * @since 2023/6/10
 */
public class NotMatchExchangeException extends RuntimeException {


    public NotMatchExchangeException(String message) {
        super(message);
    }

}
