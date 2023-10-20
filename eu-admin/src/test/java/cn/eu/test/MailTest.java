package cn.eu.test;

import cn.eu.message.enums.MailSendType;
import cn.eu.message.handler.dispatcher.MessageDispatcher;
import cn.eu.message.model.Mail;
import cn.eu.message.model.MailAttachment;
import cn.eu.message.model.Sms;
import cn.eu.message.service.IMailService;
import cn.hutool.core.collection.CollUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zhaoeryu
 * @since 2023/6/10
 */
@SpringBootTest
public class MailTest {

    @Autowired
    IMailService mailService;
    @Autowired
    MessageDispatcher messageDispatcher;

    @Test
    public void testSendText(){
        Mail mail = new Mail();
        mail.setTo("cn.zhaoey@qq.com");
        mail.setSubject("测试主题");
        mail.setContent("测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容");
        mail.setCc(new String[]{"cn.zhaoey@gmail.com"});
//        mail.setBcc(new String[]{"cn.zhaoey@gmail.com"});

        mailService.sendText(mail);
    }

    @Test
    public void testSendHtml(){
        Mail mail = new Mail();
        mail.setTo("cn.zhaoey@qq.com");
        mail.setSubject("测试主题-发送Html");
        mail.setContent("<h1 style=\"color: red;font-size: 30px;\">HelloWorld</h1>\n" +
                "          <h1 style=\"color: blue;font-size: 20px;\">Test</h1>");
        mail.setCc(new String[]{"cn.zhaoey@gmail.com"});
//        mail.setBcc(new String[]{"cn.zhaoey@gmail.com"});
        mail.setAttachmentList(CollUtil.newArrayList(
            new MailAttachment("x.pdf", "https://successhetai-ship-manage.oss-cn-shanghai.aliyuncs.com/crew/contract/pdf/290272fcd4744b659b9f132a2a14e3d3.pdf"),
            new MailAttachment("y.jpg", "https://successhetai-ship-manage.oss-cn-shanghai.aliyuncs.com/crew/contract/jpg/b49470ccdea742478f8048792e4e3e27.jpg")
        ));


        mailService.sendHtml(mail);
    }

    @Test
    public void testExchange(){
        Mail mail = new Mail();
        mail.setSendType(MailSendType.TEXT);
        mail.setTo("cn.zhaoey@qq.com");
        mail.setSubject("测试主题");
        mail.setContent("测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容");
//        mail.setCc(new String[]{"cn.zhaoey@gmail.com"});
        messageDispatcher.dispatch(mail);
    }

    @Test
    public void testSms(){
        Sms sms = new Sms();
        messageDispatcher.dispatch(sms);
    }

}
