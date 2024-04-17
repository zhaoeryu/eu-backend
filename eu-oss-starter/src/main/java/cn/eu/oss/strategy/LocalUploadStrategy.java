package cn.eu.oss.strategy;

import cn.eu.oss.constants.OssConstants;
import cn.eu.oss.model.UploadResult;
import cn.eu.oss.properties.OssProperties;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author zhaoeryu
 * @since 2023/7/1
 */
@Slf4j
@Component(OssConstants.LOCAL_STRATEGY + OssConstants.STRATEGY_SUFFIX)
public class LocalUploadStrategy implements IUploadStrategy {

    @Autowired
    OssProperties ossProperties;
    @Autowired
    Environment environment;

    @Override
    public UploadResult upload(MultipartFile multipartFile) throws IOException {
        log.info("本地上传根路径=>{}, 服务地址=>{}", ossProperties.getLocalPath(), ossProperties.getServiceHost());
        String ext = FileUtil.extName(multipartFile.getOriginalFilename());
        ext = StrUtil.isNotBlank(ext) ? "." + ext : "";

        String fileType = null;
        try {
            fileType = FileTypeUtil.getType(multipartFile.getInputStream());
        } catch (Exception e) {
            log.error("获取文件类型失败", e);
        }
        fileType = StrUtil.blankToDefault(fileType, "files");

        String suffixPath = "/" + fileType + "/" + IdUtil.getSnowflakeNextIdStr() + ext;
        File uploadFile = new File(ossProperties.getLocalPath() + suffixPath);
        FileUtil.touch(uploadFile);
        multipartFile.transferTo(uploadFile);

        String fileUri = environment.getProperty("server.servlet.context-path", "") + ossProperties.getLocalFilePrefix() + suffixPath;
        String fileUrl = ossProperties.getServiceHost() + fileUri;
        return new UploadResult(multipartFile.getOriginalFilename(),  fileUrl, fileUri);
    }
}
