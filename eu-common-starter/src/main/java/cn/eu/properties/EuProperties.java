package cn.eu.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author zhaoeryu
 * @since 2023/6/11
 */
@Data
@ConfigurationProperties(prefix = "eu")
public class EuProperties {

    /**
     * 是否保存操作日志到数据库
     */
    private boolean saveLog = true;

    @NestedConfigurationProperty
    private RsaProperties rsa = new RsaProperties();

    @NestedConfigurationProperty
    private XssProperties xss = new XssProperties();

    @NestedConfigurationProperty
    private ThreadProperties thread = new ThreadProperties();

    @NestedConfigurationProperty
    private Knife4jProperties knife4j = new Knife4jProperties();

}
