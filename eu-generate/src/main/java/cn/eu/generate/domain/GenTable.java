package cn.eu.generate.domain;

import cn.eu.generate.enums.GenMode;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhaoeryu
 * @since 2023/6/27
 */
@Data
@TableName("gen_table")
public class GenTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String id;

    /**
     * 包路径
     */
    @NotBlank(message = "包路径不能为空")
    private String packageName;
    /**
     * 模块名
     */
    @NotBlank(message = "模块名不能为空")
    private String moduleName;
    /**
     * 功能分组
     */
    private String funcGroup;
    /**
     * 作者
     */
    private String author;
    /**
     * 表注释
     */
    private String tableComment;
    /**
     * 表名
     */
    @NotBlank(message = "表名不能为空")
    private String tableName;
    /**
     * 删除时，提示使用的字段
     */
    private String delShowField;
    /**
     * 生成模式
     * @see GenMode#ordinal()
     */
    private Integer genMode;
    /**
     * 详情页顶部显示字段
     */
    private String detailHeaderFieldKey;
    /**
     * crud编辑模式
     * @see cn.eu.generate.enums.CrudEditMode
     */
    private String crudEditMode;

    @ExcelIgnore
    @TableField(fill = FieldFill.INSERT)
    private String createBy;
    @ExcelProperty("创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @ExcelIgnore
    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;
    @ExcelIgnore
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
