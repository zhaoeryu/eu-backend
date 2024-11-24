package cn.eu.oss.spi;

import cn.eu.common.spi.SecurityExcludeUriPatternsSpi;
import cn.eu.common.utils.SpringContextHolder;
import cn.eu.oss.enums.OssStrategy;
import cn.eu.oss.properties.OssProperties;
import cn.hutool.core.collection.CollUtil;

import java.util.List;

/**
 * @author Eu.z
 * @since 2024/11/24
 */
public class OssSecurityExcludeUriPatternsSpiImpl implements SecurityExcludeUriPatternsSpi {
    @Override
    public List<String> getExcludePatterns() {
        OssProperties ossProperties = SpringContextHolder.getBean(OssProperties.class);
        if (OssStrategy.LOCAL == ossProperties.getStrategy()) {
            return CollUtil.newArrayList(ossProperties.getLocalFilePrefix() + "/**");
        }
        return null;
    }
}
