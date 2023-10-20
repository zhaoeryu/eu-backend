package cn.eu.quartz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhaoeryu
 * @since 2023/6/13
 */
@AllArgsConstructor
@Getter
public enum QuartzJobConcurrent {

    /**
     * 允许
     */
    ENABLED(0),
    /**
     * 禁止
     */
    DISABLED(1);

    private final int value;

}
