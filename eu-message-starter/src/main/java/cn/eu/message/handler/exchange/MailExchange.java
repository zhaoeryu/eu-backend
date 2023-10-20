package cn.eu.message.handler.exchange;

import cn.eu.message.enums.MailSendType;
import cn.eu.message.handler.IMessageExchange;
import cn.eu.message.model.Mail;
import cn.eu.message.service.IMailService;
import cn.eu.common.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhaoeryu
 * @since 2023/6/10
 */
@Component
public class MailExchange implements IMessageExchange {

    @Autowired
    IMailService mailService;

    @Override
    public boolean support(Message message) {
        return message instanceof Mail;
    }

    @Override
    public void exchange(Message message) {
        Mail mail = (Mail) message;
        if (MailSendType.TEXT.equals(mail.getSendType())) {
            mailService.sendText(mail);
        } else {
            mailService.sendHtml(mail);
        }
    }
}
