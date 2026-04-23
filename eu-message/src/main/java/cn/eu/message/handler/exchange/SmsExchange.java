package cn.eu.message.handler.exchange;

import cn.eu.common.exception.ServerException;
import cn.eu.common.model.Message;
import cn.eu.message.handler.IMessageExchange;
import cn.eu.message.model.Sms;
import org.springframework.stereotype.Component;

/**
 * @author zhaoeryu
 * @since 2023/6/10
 */
@Component
public class SmsExchange implements IMessageExchange {

    @Override
    public boolean support(Message message) {
        return message instanceof Sms;
    }

    @Override
    public void exchange(Message message) {
        Sms sms = (Sms) message;
        throw new ServerException("系统暂未接入短信平台");
    }
}
