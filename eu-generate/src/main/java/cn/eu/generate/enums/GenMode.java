package cn.eu.generate.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhaoeryu
 * @since 2023/6/28
 */
@Getter
@AllArgsConstructor
public enum GenMode {

    // 本地生成
    GENERATE,
    // 下载
    DOWNLOAD

}
