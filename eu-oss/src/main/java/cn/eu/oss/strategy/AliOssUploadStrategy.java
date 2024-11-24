package cn.eu.oss.strategy;

import cn.eu.oss.constants.OssConstants;
import cn.eu.oss.model.UploadResult;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author zhaoeryu
 * @since 2023/7/1
 */
@Component(OssConstants.ALI_OSS_STRATEGY + OssConstants.STRATEGY_SUFFIX)
public class AliOssUploadStrategy implements IUploadStrategy {

    @Override
    public UploadResult upload(MultipartFile multipartFile) throws IOException {
        throw new RuntimeException("阿里云OSS暂未实现");
    }
}
