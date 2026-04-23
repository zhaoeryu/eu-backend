package cn.eu.common.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.eu.common.model.ResultBody;
import cn.eu.common.utils.ExceptionUtil;
import cn.eu.common.utils.MessageUtils;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Optional;

/**
 * @author zhaoeryu
 * @since 2023/6/1
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 基础异常处理
     */
    @ExceptionHandler(Exception.class)
    public ResultBody exception(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        return buildBody(ex, request.getRequestURI());
    }

    /**
     * 处理框架异常
     */
    @ExceptionHandler(value = EuException.class)
    public ResultBody handleEuException(HttpServletRequest request, EuException ex) {
        return buildBody(ex, ex.getMessage(), ex.getCode(), request.getRequestURI());
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNoResourceFound(NoResourceFoundException ex) {
        log.warn("静态资源未找到: {}", ex.getResourcePath());
    }

    /**
     * 处理实体校验异常 (BindException)
     * 适用于 @RequestParam, @PathVariable 等校验失败
     */
    @ExceptionHandler(BindException.class)
    public ResultBody bindException(BindException ex, HttpServletRequest request, HttpServletResponse response) {
        try {
            String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
            return buildBody(ex, errorMessage, EuErrorCode.PARAMS_VALID_ERROR.getCode(), request.getRequestURI());
        } catch (Exception e) { }
        return buildBody(ex, EuErrorCode.PARAMS_VALID_ERROR, request.getRequestURI());
    }

    /**
     * 处理实体校验异常 (MethodArgumentNotValidException)
     * 适用于 @RequestBody 校验失败
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultBody handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String message = Optional.ofNullable(ex.getBindingResult().getFieldError())
                .map(FieldError::getDefaultMessage)
                .orElse(ex.getMessage());
        return buildBody(ex, message, EuErrorCode.PARAMS_VALID_ERROR.getCode(), request.getRequestURI());
    }
    /**
     * 处理参数类型转换错误
     */
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResultBody handleMethodArgumentTypeMismatchException(HttpServletRequest request, MethodArgumentTypeMismatchException ex) {
        return buildBody(ex, EuErrorCode.PARAMS_CONVERT_ERROR, request.getRequestURI());
    }

    /**
     * 处理请求参数缺失异常
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResultBody missingServletRequestParameterException(MissingServletRequestParameterException ex, HttpServletRequest request, HttpServletResponse response) {
        return buildBody(ex, EuErrorCode.PARAMS_MISSING_ERROR, request.getRequestURI());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResultBody handleIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest request, HttpServletResponse response) {
        return buildBody(ex, ex.getMessage(), EuErrorCode.PARAMS_ILLEGAL_ERROR.getCode(), request.getRequestURI());
    }

    /**
     * 处理因为数据库唯一索引导致的异常
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public ResultBody duplicateKeyException(DuplicateKeyException ex, HttpServletRequest request, HttpServletResponse response) {
        String errorMessage = StrUtil.blankToDefault(Optional.ofNullable(ex.getCause()).map(Throwable::getMessage).orElse(null), ex.getMessage());
        if (StrUtil.isBlank(errorMessage)) {
            return buildBody(ex, request.getRequestURI());
        }
        String duplicateKeyMessage = ExceptionUtil.getDuplicateKeyMessage(errorMessage);
        if (StrUtil.isNotBlank(duplicateKeyMessage)) {
            return buildBody(ex, duplicateKeyMessage, EuErrorCode.DUPLICATE_KEY_ERROR.getCode(), request.getRequestURI());
        }

        return buildBody(ex, request.getRequestURI());
    }

    @ExceptionHandler(NotLoginException.class)
    public ResultBody notLoginException(NotLoginException ex, HttpServletRequest request, HttpServletResponse response) {
        String message = null;
        switch (ex.getType()) {
            case NotLoginException.NOT_TOKEN:
                message = MessageUtils.message("error.token.NOT_TOKEN");
                break;
            case NotLoginException.INVALID_TOKEN:
                message = MessageUtils.message("error.token.INVALID_TOKEN");
                break;
            case NotLoginException.TOKEN_TIMEOUT:
                message = MessageUtils.message("error.token.TOKEN_TIMEOUT");
                break;
            case NotLoginException.BE_REPLACED:
                message = MessageUtils.message("error.token.BE_REPLACED");
                break;
            case NotLoginException.KICK_OUT:
                message = MessageUtils.message("error.token.KICK_OUT");
                break;
            default:
                message = MessageUtils.message("error.token.NOT_LOGIN");
        }
        return buildBody(ex, message, EuErrorCode.SYS_NOT_LOGIN.getCode(), request.getRequestURI());
    }

    @ExceptionHandler(NotPermissionException.class)
    public ResultBody notPermissionException(NotPermissionException ex, HttpServletRequest request, HttpServletResponse response) {
        return buildBody(ex, ex.getMessage(), EuErrorCode.SYS_NOT_PERMISSION.getCode(), request.getRequestURI());
    }
    @ExceptionHandler(NotRoleException.class)
    public ResultBody notRoleException(NotRoleException ex, HttpServletRequest request, HttpServletResponse response) {
        return buildBody(ex, ex.getMessage(), EuErrorCode.SYS_NOT_ROLE.getCode(), request.getRequestURI());
    }

    private static ResultBody buildBody(Exception ex, String uri) {
        String errMessage = StrUtil.blankToDefault(Optional.ofNullable(ex.getCause()).map(Throwable::getMessage).orElse(null), ex.getMessage());
        return buildBody(ex, errMessage, null, uri);
    }

    private static ResultBody buildBody(Exception ex, ErrorCode errorCode, String uri) {
        return buildBody(ex, errorCode.getMessage(), errorCode.getCode(), uri);
    }

    private static ResultBody buildBody(Exception ex, String message, Integer resultCode, String uri) {
        log.error("请求异常，uri：{}", uri, ex);
        if (StrUtil.isBlank(message)) {
            message = EuErrorCode.SERVER_ERROR.getMessage();
        }
        if (resultCode == null) {
            resultCode = EuErrorCode.SERVER_ERROR.getCode();
        }
        return ResultBody.failed()
                .code(resultCode)
                .msg(message)
                .path(uri);
    }

}
