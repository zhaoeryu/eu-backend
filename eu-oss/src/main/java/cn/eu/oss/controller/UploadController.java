package cn.eu.oss.controller;

import cn.eu.common.annotation.Log;
import cn.eu.common.enums.BusinessType;
import cn.eu.oss.model.UploadResult;
import cn.eu.oss.properties.OssProperties;
import cn.eu.oss.strategy.UploadStrategyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件上传
 * @author zhaoeryu
 * @since 2023/7/1
 */
@RequestMapping("/api/upload")
@RestController
public class UploadController {

    @Autowired
    UploadStrategyContext uploadStrategyContext;
    @Autowired
    OssProperties ossProperties;

    /**
     * 上传单个文件
     */
    @Log(title = "上传单文件", businessType = BusinessType.OTHER)
    @PostMapping("/uploadFile")
    public UploadResult uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        return uploadStrategyContext.upload(multipartFile);
    }

    /**
     * 上传多个文件
     */
    @Log(title = "上传多文件", businessType = BusinessType.OTHER)
    @PostMapping("/uploadFiles")
    public List<UploadResult> uploadFiles(@RequestParam("files") MultipartFile[] multipartFiles) throws IOException {

        List<UploadResult> resultList = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            resultList.add(uploadStrategyContext.upload(multipartFile));
        }

        return resultList;
    }

}
