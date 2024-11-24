package cn.eu.message.service;

import cn.eu.message.model.Mail;

/**
 * @author zhaoeryu
 * @since 2023/6/10
 */
public interface IMailService {

    /**
     * 纯文本发送
     * @param mail 邮件参数
     */
    void sendText(Mail mail);

    /**
     * html格式发送，可携带附件
     * @param mail 邮件参数
     */
    void sendHtml(Mail mail);

}
