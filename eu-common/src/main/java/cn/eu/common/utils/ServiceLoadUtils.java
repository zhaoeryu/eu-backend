package cn.eu.common.utils;

import cn.eu.common.spi.SecurityExcludeUriPatternsSpi;
import cn.hutool.core.stream.StreamUtil;

import java.util.List;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

/**
 * @author Eu.z
 * @since 2024/11/24
 */
public class ServiceLoadUtils {

    /**
     * 加载各模块下单独自定义的不需要登录的uri规则
     */
    public static List<String> loadSecurityExcludeUriPatterns() {
        return StreamUtil.of(ServiceLoader.load(SecurityExcludeUriPatternsSpi.class))
                .map(SecurityExcludeUriPatternsSpi::getExcludePatterns)
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

}
