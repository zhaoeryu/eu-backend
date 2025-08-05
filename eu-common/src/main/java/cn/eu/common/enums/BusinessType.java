package cn.eu.common.enums;

import cn.eu.common.annotation.IEuEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 业务操作类型
 * @author zhaoeryu
 */
@Getter
@AllArgsConstructor
public enum BusinessType implements IEuEnum<Integer> {
    /**
     * 其它
     */
    OTHER(0, "其它"),

    /**
     * 查询
     */
    QUERY(1, "查询"),

    /**
     * 新增
     */
    INSERT(2, "新增"),

    /**
     * 修改
     */
    UPDATE(3, "修改"),

    /**
     * 删除
     */
    DELETE(4, "删除"),

    /**
     * 导出
     */
    EXPORT(5, "导出"),
    /**
     * 导出模版
     */
    EXPORT_TEMPLATE(6, "导出模版"),
    /**
     * 导入
     */
    IMPORT(7, "导入"),
    /**
     * 登录
     */
    LOGIN(8, "登录"),
    /**
     * 登出
     */
    LOGOUT(9, "登出"),
    /**
     * 踢人下线
     */
    KICKOUT(10, "踢人下线"),
    /**
     * 强制注销
     */
    FORCE_LOGOUT(11, "强制注销"),
    /**
     * 暂停/恢复任务
     */
    PAUSE_RESUME_JOB(12, "暂停/恢复任务"),
    /**
     * 执行任务
     */
    EXEC_JOB(13, "执行任务");


    private final Integer value;
    private final String desc;

}
