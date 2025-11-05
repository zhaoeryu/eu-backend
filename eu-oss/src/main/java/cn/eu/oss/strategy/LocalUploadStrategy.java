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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

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
        String extName = FileUtil.extName(multipartFile.getOriginalFilename());
        String ext = StrUtil.isNotBlank(extName) ? "." + extName : "";
        // 文件大小 kb
        Long fileSize = multipartFile.getSize() / 1024;

        String fileType = null;
        try {
            fileType = FileTypeUtil.getType(multipartFile.getInputStream());
        } catch (Exception e) {
            log.error("获取文件类型失败", e);
        }
        fileType = StrUtil.blankToDefault(fileType, "files");

        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String filePath = File.separator + new StringJoiner(File.separator)
                .add(date)
                .add(fileType)
                .add(IdUtil.getSnowflakeNextIdStr() + ext);
        File uploadFile = new File(File.separator + ossProperties.getLocalPath() + filePath);
        FileUtil.touch(uploadFile);
        multipartFile.transferTo(uploadFile);

        String uriSuffix = ossProperties.getLocalFilePrefix() + StrUtil.replace(filePath, File.separator, "/");
        String fileUri = environment.getProperty("server.servlet.context-path", "") + uriSuffix;
        String fileUrl = ossProperties.getServiceHost() + fileUri;

        return new UploadResult(multipartFile.getOriginalFilename(),  fileUrl, fileUri, extName, fileSize);
    }
}
