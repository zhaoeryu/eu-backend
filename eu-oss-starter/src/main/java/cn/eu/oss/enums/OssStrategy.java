package cn.eu.oss.enums;

import cn.eu.oss.constants.OssConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhaoeryu
 * @since 2023/7/1
 */
@Getter
@AllArgsConstructor
public enum OssStrategy {

    LOCAL(OssConstants.LOCAL_STRATEGY),
    ALI_OSS(OssConstants.ALI_OSS_STRATEGY);

    private final String value;

}
