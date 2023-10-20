package cn.eu.oss.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.eu.common.annotation.Log;
import cn.eu.common.enums.BusinessType;
import cn.eu.oss.model.UploadResult;
import cn.eu.oss.properties.OssProperties;
import cn.eu.oss.strategy.LocalUploadStrategy;
import cn.eu.oss.strategy.UploadStrategyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件上传
 * @author zhaoeryu
 * @since 2023/7/1
 */
@RequestMapping("/upload")
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

    /**
     * 下载文件 本地文件下载
     * @param path 文件路径
     */
    @SaIgnore
    @GetMapping("/download")
    public ResponseEntity download(String path) throws MalformedURLException {

        File file = new File(ossProperties.getLocalPath() + path);
        Assert.isTrue(file.exists(), "文件不存在");

        UrlResource urlResource = new UrlResource(file.toURI());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + urlResource.getFilename())
                .body(urlResource);
    }

}
