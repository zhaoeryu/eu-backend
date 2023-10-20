package cn.eu.message.model;

import cn.eu.common.model.Message;
import cn.eu.message.enums.MailSendType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/6/10
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Mail extends Message {

    private static final long serialVersionUID = 1L;

    /**
     * 发送给谁
     */
    private String to;

    /**
     * 发送主题
     */
    private String subject;

    /**
     * 发送内容
     */
    private String content;

    /**
     * 抄送人
     */
    private String[] cc;

    /**
     * 密送
     */
    private String[] bcc;

    /**
     * 附件
     */
    private List<MailAttachment> attachmentList;

    /**
     * 发送方式
     * @see cn.eu.message.enums.MailSendType
     */
    private MailSendType sendType;
}
