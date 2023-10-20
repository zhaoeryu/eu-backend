package cn.eu.oss.properties;

import cn.eu.oss.constants.OssConstants;
import cn.eu.oss.enums.OssStrategy;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhaoeryu
 * @since 2023/7/1
 */
@Data
@ConfigurationProperties(prefix = "eu.oss")
public class OssProperties {

    /**
     * 上传策略：默认本地
     */
    private OssStrategy strategy = OssStrategy.LOCAL;

    /**
     * 本地上传路径
     */
    private String localPath = OssConstants.DEFAULT_UPLOAD_PATH;

    /**
     * 服务HOST
     */
    private String serviceHost = OssConstants.DEFAULT_SERVICE_HOST;
}
