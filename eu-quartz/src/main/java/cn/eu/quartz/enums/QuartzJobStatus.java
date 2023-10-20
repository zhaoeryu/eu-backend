package cn.eu.quartz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhaoeryu
 * @since 2023/6/13
 */
@AllArgsConstructor
@Getter
public enum QuartzJobStatus {

    /**
     * 正常
     */
    NORMAL(0),
    /**
     * 暂停
     */
    PAUSE(1);

    private final int value;

}
