package cn.eu.oss.strategy;

import cn.eu.oss.constants.OssConstants;
import cn.eu.oss.enums.OssStrategy;
import cn.eu.oss.model.UploadResult;
import cn.eu.oss.properties.OssProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author zhaoeryu
 * @since 2023/7/1
 */
@Slf4j
@Component
public class UploadStrategyContext {

    private final IUploadStrategy uploadStrategy;

    public UploadStrategyContext(OssProperties uploadProperties, ApplicationContext applicationContext) {
        log.info("初始化上传策略：{}", uploadProperties.getStrategy().getValue());
        if (uploadProperties.getStrategy() == OssStrategy.LOCAL) {
            log.info("本地上传根路径=>{}, 服务地址=>{}", uploadProperties.getLocalPath(), uploadProperties.getServiceHost());
        }
        uploadStrategy = applicationContext.getBean(uploadProperties.getStrategy().getValue() + OssConstants.STRATEGY_SUFFIX, IUploadStrategy.class);
    }

    public UploadResult upload(MultipartFile file) throws IOException {
        return this.uploadStrategy.upload(file);
    }
}
