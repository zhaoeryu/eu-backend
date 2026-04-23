package cn.eu.common.core.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhaoeryu
 * @since 2023/5/31
 */
@Data
public abstract class BaseEntity implements Serializable {

    public static final String FIELD_CREATE_BY = "create_by";
    public static final String FIELD_UPDATE_BY = "update_by";
    public static final String FIELD_CREATE_BY_NAME = "create_by_name";
    public static final String FIELD_UPDATE_BY_NAME = "update_by_name";
    public static final String FIELD_CREATE_TIME = "create_time";
    public static final String FIELD_UPDATE_TIME = "update_time";
    public static final String FIELD_DEL_FLAG = "del_flag";
    public static final String FIELD_REMARK = "remark";

}
