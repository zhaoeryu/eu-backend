package cn.eu.common.exception;

/**
 * 参数非法异常
 *
 * @author Eu.z
 * @since 2024/11/24
 */
public class ParamsIllegalException extends EuException {

    public ParamsIllegalException() {
        super(EuErrorCode.PARAMS_ILLEGAL_ERROR);
    }

    public ParamsIllegalException(String message) {
        super(EuErrorCode.PARAMS_ILLEGAL_ERROR.getCode(), message);
    }

}
