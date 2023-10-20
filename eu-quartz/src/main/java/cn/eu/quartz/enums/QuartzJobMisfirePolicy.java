package cn.eu.quartz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhaoeryu
 * @since 2023/6/13
 */
@Getter
@AllArgsConstructor
public enum QuartzJobMisfirePolicy {

    /**
     * 默认
     */
    DEFAULT(0),
    /**
     * 立即触发执行
     */
    IGNORE_MISFIRES(1),
    /**
     * 触发一次执行
     */
    FIRE_AND_PROCEED(2),
    /**
     * 不触发立即执行
     */
    DO_NOTHING(3);

    private final Integer value;

    public static QuartzJobMisfirePolicy getByValue(Integer value) {
        for (QuartzJobMisfirePolicy policy : QuartzJobMisfirePolicy.values()) {
            if (policy.getValue().equals(value)) {
                return policy;
            }
        }
        return null;
    }

}
