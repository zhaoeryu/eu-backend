package cn.eu.message.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhaoeryu
 * @since 2023/6/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailAttachment implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fileName;
    private String fileUrl;

}
