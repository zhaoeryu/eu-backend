package cn.eu.oss.constants;

import java.io.File;

/**
 * @author zhaoeryu
 * @since 2023/7/1
 */
public class OssConstants {

    /**
     * 上传策略：本地
     */
    public static final String LOCAL_STRATEGY = "local";
    /**
     * 上传策略：阿里云OSS
     */
    public static final String ALI_OSS_STRATEGY = "ali-oss";


    /**
     * 上传策略后缀
     */
    public static final String STRATEGY_SUFFIX = "UploadStrategy";

    /**
     * 默认上传路径：项目根目录下的uploads文件夹
     */
    public static final String DEFAULT_UPLOAD_PATH = System.getProperty("user.dir") + File.separator + "uploads";

    /**
     * 默认：服务HOST
     */
    public static final String DEFAULT_SERVICE_HOST = "http://localhost:8000";
}
