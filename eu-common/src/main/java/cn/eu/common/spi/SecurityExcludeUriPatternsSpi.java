package cn.eu.common.spi;

import java.util.List;

/**
 * @author Eu.z
 * @since 2024/11/24
 */
public interface SecurityExcludeUriPatternsSpi {

    List<String> getExcludePatterns();

}
