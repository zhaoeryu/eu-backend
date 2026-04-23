package cn.eu.common.exception;

/**
 * 参数校验异常
 *
 * @author Eu.z
 * @since 2024/11/24
 */
public class ParamsValidException extends EuException {

    public ParamsValidException() {
        super(EuErrorCode.PARAMS_VALID_ERROR);
    }

    public ParamsValidException(String message) {
        super(EuErrorCode.PARAMS_VALID_ERROR.getCode(), message);
    }

}
