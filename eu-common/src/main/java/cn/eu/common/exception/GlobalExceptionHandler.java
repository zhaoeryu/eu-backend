package cn.eu.common.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.eu.common.model.IError;
import cn.eu.common.model.ResultBody;
import cn.eu.common.utils.MessageUtils;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhaoeryu
 * @since 2023/6/1
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    final String REGX_DUPLICATE_KEY = "Duplicate entry '(.*)' for key '(.*)'";

    /**
     * 基础异常处理
     */
    @ExceptionHandler(Exception.class)
    public ResultBody exception(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        return buildBody(ex, request.getRequestURI());
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNoResourceFound(NoResourceFoundException ex) {
        log.warn("静态资源未找到: {}", ex.getResourcePath());
    }

    @ExceptionHandler(BindException.class)
    public ResultBody bindException(BindException ex, HttpServletRequest request, HttpServletResponse response) {
        try {
            String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
            return buildBody(errorMessage, IError.ERROR.getCode(), request.getRequestURI());
        } catch (Exception e) {
            return buildBody(ex, request.getRequestURI());
        }
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
        // 提取重复字段
        Matcher matcher = Pattern.compile(REGX_DUPLICATE_KEY).matcher(errorMessage);
        if (matcher.find()) {
            String duplicateField = matcher.group(2);
            String duplicateFieldValue = matcher.group(1);
            if (StrUtil.isNotBlank(duplicateField)) {
                return buildBody(StrUtil.format("[{}]({}){}", duplicateField, duplicateFieldValue, MessageUtils.message("error.duplicate_key")), IError.ERROR.getCode(), request.getRequestURI());
            }
        }
        return buildBody(ex, request.getRequestURI());
    }

    /**
     * 处理请求参数缺失异常
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResultBody missingServletRequestParameterException(MissingServletRequestParameterException ex, HttpServletRequest request, HttpServletResponse response) {
        String errorMessage = StrUtil.format("{}[{}]", MessageUtils.message("error.missing_request_param") , ex.getParameterName());
        return buildBody(errorMessage, IError.ERROR.getCode(), request.getRequestURI());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResultBody dataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request, HttpServletResponse response) {
        String errorMessage = StrUtil.blankToDefault(Optional.ofNullable(ex.getCause()).map(Throwable::getMessage).orElse(null), ex.getMessage());
        if (StrUtil.isBlank(errorMessage)) {
            return buildBody(ex, request.getRequestURI());
        }
        return buildBody(errorMessage, IError.ERROR.getCode(), request.getRequestURI());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        String message = Optional.ofNullable(e.getBindingResult().getFieldError()).map(FieldError::getDefaultMessage)
                .orElse(e.getMessage());
        return buildBody(message, null, request.getRequestURI());
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
        return buildBody(message, IError.NOT_LOGIN.getCode(), request.getRequestURI());
    }

    @ExceptionHandler(NotPermissionException.class)
    public ResultBody notPermissionException(NotPermissionException ex, HttpServletRequest request, HttpServletResponse response) {
        return buildBody(ex.getMessage(), IError.NOT_PERMISSION.getCode(), request.getRequestURI());
    }
    @ExceptionHandler(NotRoleException.class)
    public ResultBody notRoleException(NotRoleException ex, HttpServletRequest request, HttpServletResponse response) {
        return buildBody(ex.getMessage(), IError.NOT_PERMISSION.getCode(), request.getRequestURI());
    }

    private static ResultBody buildBody(Exception exception, String uri) {
        log.error(exception.getMessage(), exception);
        String errMessage = StrUtil.blankToDefault(Optional.ofNullable(exception.getCause()).map(Throwable::getMessage).orElse(null), exception.getMessage());
        return buildBody(errMessage, null, uri);
    }

    private static ResultBody buildBody(String message, Integer resultCode, String uri) {
        if (StrUtil.isBlank(message)) {
            message = IError.ERROR.getMessage();
        }
        if (resultCode == null) {
            resultCode = IError.ERROR.getCode();
        }
        ResultBody resultBody = ResultBody.failed()
                .code(resultCode)
                .msg(message)
                .path(uri);
        log.error("==> error:{}" , resultBody);
        return resultBody;
    }

}
