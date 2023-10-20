package cn.eu.oss.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhaoeryu
 * @since 2023/7/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadResult {

    private String name;
    private String link;

}
