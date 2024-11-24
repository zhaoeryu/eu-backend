package cn.eu.oss.strategy;

import cn.eu.oss.model.UploadResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 上传策略
 * @author zhaoeryu
 * @since 2023/7/1
 */
public interface IUploadStrategy {

    /**
     * 上传文件
     */
    UploadResult upload(MultipartFile multipartFile) throws IOException;

}
