package cn.eu.common.exception;

/**
 * 重复提交异常
 *
 * @author Eu.z
 * @since 2024/11/24
 */
public class RepeatSubmitException extends EuException {

    public RepeatSubmitException() {
        super(EuErrorCode.REPEAT_SUBMIT);
    }

    public RepeatSubmitException(String message) {
        super(EuErrorCode.REPEAT_SUBMIT.getCode(), message);
    }

}
