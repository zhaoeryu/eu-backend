package cn.eu.generate.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhaoeryu
 * @since 2023/6/27
 */
@Data
@NoArgsConstructor
public class GenerateTemplateDto {

    private String path;
    private String name;
    private String type;
    private String code;

    public GenerateTemplateDto(String path, String name, String type) {
        this.path = path;
        this.name = name;
        this.type = type;
    }
}
