package cn.eu.common.properties;

import lombok.Data;

/**
 * Xss 配置
 * @author zhaoeryu
 * @since 2023/6/5
 */
@Data
public class XssProperties {

    private boolean enabled = true;

    private String excludes;

    private String urlPatterns;
}
