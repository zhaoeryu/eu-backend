package cn.eu.message.service.impl;

import cn.eu.message.model.Mail;
import cn.eu.message.model.MailAttachment;
import cn.eu.message.service.IMailService;
import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.activation.DataSource;
import javax.activation.URLDataSource;
import javax.mail.internet.MimeMessage;
import java.net.URL;
import java.util.Date;

/**
 * @author zhaoeryu
 * @since 2023/6/10
 */
@Slf4j
@Service
public class MailServiceImpl implements IMailService {

    @Autowired
    JavaMailSender mailSender;
    @Autowired
    MailProperties mailProperties;

    @Override
    public void sendText(Mail mail) {
        log.info("准备发送邮件：{} => {}", mailProperties.getUsername(), mail.getTo());
        Assert.notNull(mailProperties.getUsername(), "请在application.properties中配置 eu.mail.from-mail=<发送者邮箱>");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailProperties.getUsername());
        message.setTo(mail.getTo());
        message.setCc(mail.getCc());
        message.setBcc(mail.getBcc());
        message.setSentDate(new Date());
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        mailSender.send(message);
        log.info("发送邮件成功：{} => {}", mailProperties.getUsername(), mail.getTo());
    }

    @Override
    public void sendHtml(Mail mail) {
        log.info("准备发送邮件：{} => {}", mailProperties.getUsername(), mail.getTo());
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(mailProperties.getUsername());
            helper.setTo(mail.getTo());
            if (mail.getCc() != null) {
                helper.setCc(mail.getCc());
            }
            if (mail.getBcc() != null) {
                helper.setBcc(mail.getBcc());
            }
            helper.setSentDate(new Date());
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getContent(), true);

            if (CollUtil.isNotEmpty(mail.getAttachmentList())) {
                // 添加附件
                for (MailAttachment attachment : mail.getAttachmentList()) {
                    URL url = new URL(attachment.getFileUrl());
                    DataSource dataSource=new URLDataSource(url);
                    helper.addAttachment(attachment.getFileName(), dataSource);
                }
            }

            mailSender.send(message);
            log.info("发送邮件成功：{} => {}", mailProperties.getUsername(), mail.getTo());
        } catch (Exception e) {
            log.error("发送邮件异常：{} => {}", mailProperties.getUsername(), mail.getTo());
            log.error("发送邮件异常：" + e.getMessage(), e);
        }
    }

}
