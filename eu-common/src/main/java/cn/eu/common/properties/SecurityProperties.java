package cn.eu.common.properties;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 安全配置
 *
 * @author Eu.z
 * @since 2024/11/24
 */
@Data
public class SecurityProperties {

    /**
     * 不校验登录状态的uri规则列表
     */
    private List<String> excludeUriPatterns = new ArrayList<>();

}
