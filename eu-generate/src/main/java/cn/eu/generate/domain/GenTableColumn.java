package cn.eu.generate.domain;

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
@TableName("gen_table_column")
public class GenTableColumn implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String id;

    @NotBlank(message = "表名不能为空")
    private String tableName;

    /**
     * 字段名称
     */
    @NotBlank(message = "字段名称不能为空")
    private String columnName;
    /**
     * 字段描述
     */
    private String columnComment;
    /**
     * 调整后的字段描述
     */
    private String finalColumnComment;
    /**
     * 字段键
     */
    private String columnKey;
    @NotBlank(message = "字段类型不能为空")
    private String columnType;
    private Boolean autoPk;
    private Integer columnSort;
    /**
     * 是否不为空
     */
    private Boolean notNull;
    /**
     * java字段类型
     */
    @NotBlank(message = "java字段类型不能为空")
    private String javaType;
    /**
     * java字段名称
     */
    @NotBlank(message = "java字段名称不能为空")
    private String javaField;

    /**
     * 表格展示字段
     */
    @TableField(exist = false)
    private String tableShowField;

    /**
     * 字段长度
     */
    private Integer columnLength;
    /**
     * 是否导出
     */
    private Boolean excelExport;
    /**
     * 是否在列表显示
     */
    private Boolean tableShow;
    /**
     * 是否在表单显示
     */
    private Boolean formShow;
    /**
     * js字段类型
     */
    @NotBlank(message = "js字段类型不能为空")
    private String jsType;
    /**
     * 表单类型
     */
    private String formType;
    /**
     * 查询方式
     */
    private String queryType;

    /**
     * 关联字典
     */
    private String dictKey;

    /**
     * 查询区域查询
     */
    private Boolean areaQuery;
    /**
     * 是否表头里查询
     */
    private Boolean tableHeaderQuery;

    /**
     * 默认是否可见
     */
    private Boolean defaultVisible;

    /**
     * 关联枚举
     */
    private String enumKey;

    /**
     * 是否内置表单类型
     */
    @TableField(exist = false)
    private Boolean builtInFormType;

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
