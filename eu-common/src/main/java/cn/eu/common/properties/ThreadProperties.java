package cn.eu.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author zhaoeryu
 * @since 2023/7/1
 */
@Data
public class ThreadProperties {

    @NestedConfigurationProperty
    private AsyncThreadProperties async = new AsyncThreadProperties();

}
