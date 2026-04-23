package cn.eu.common.model;


import cn.eu.common.exception.ErrorCode;
import cn.eu.common.exception.EuErrorCode;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import io.swagger.v3.oas.annotations.Hidden;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Optional;

/**
 * @author zhaoey
 */
public class ResultBody extends LinkedHashMap<String,Object> implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String CODE_KEY = "code";
    private static final String MSG_KEY = "msg";
    private static final String TIMESTAMP_KEY = "timestamp";
    private static final String PATH_KEY = "path";
    private static final String DATA_KEY = "data";

    public ResultBody() {
        super();
        this.code(EuErrorCode.SUCCESS.getCode()).msg(EuErrorCode.SUCCESS.getMessage());
        this.put(TIMESTAMP_KEY, System.currentTimeMillis());
    }

    public ResultBody(ErrorCode error) {
        super();
        this.code(error.getCode()).msg(error.getMessage());
        this.put(TIMESTAMP_KEY, System.currentTimeMillis());
    }

    public static ResultBody of(ErrorCode error) {
        return new ResultBody(error);
    }

    public static ResultBody ok() {
        return new ResultBody();
    }

    public static ResultBody failed() {
        return failed(EuErrorCode.SYSTEM_BUSY.getMessage());
    }
    public static ResultBody failed(String msg) {
        return new ResultBody().code(EuErrorCode.SYSTEM_BUSY.getCode()).msg(msg);
    }

    @Hidden
    public boolean isSuccess(){
        Object code = this.get(CODE_KEY);
        return code != null && Objects.equals(EuErrorCode.SUCCESS.getCode(), code);
    }

    public ResultBody code(int code) {
        this.put(CODE_KEY,code);
        return this;
    }

    public ResultBody msg(String message) {
        this.put(MSG_KEY,message);
        return this;
    }

    public ResultBody data(Object data){
        this.put(DATA_KEY,data);
        return this;
    }

    public ResultBody path(String path) {
        this.put(PATH_KEY, path);
        return this;
    }

    public String getCode(){
        return Optional.ofNullable(this.get(CODE_KEY)).map(String::valueOf).orElse(null);
    }

    public String getMsg(){
        return Optional.ofNullable(this.get(MSG_KEY)).map(String::valueOf).orElse(null);
    }
    public Object getData(){
        return this.get(DATA_KEY);
    }

    public ResultBody putValue(String key, Object value){
        super.put(key,value);
        return this;
    }

    public String toJsonString() {
        return JSONObject.toJSONString(this);
    }
}
