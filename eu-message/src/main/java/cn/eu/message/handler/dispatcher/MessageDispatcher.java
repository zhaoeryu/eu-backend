package cn.eu.message.handler.dispatcher;

import cn.eu.common.exception.NotMatchExchangeException;
import cn.eu.message.handler.IMessageExchange;
import cn.eu.common.model.Message;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author zhaoeryu
 * @since 2023/6/10
 */
@Component
public class MessageDispatcher implements ApplicationContextAware {

    private Collection<IMessageExchange> exchanges;

    public void dispatch(Message message) {
        for (IMessageExchange exchange : exchanges) {
            if (exchange.support(message)) {
                exchange.exchange(message);
                return;
            }
        }
        throw new NotMatchExchangeException(String.format("[%s]未实现exchange", message.getClass()));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.exchanges = applicationContext.getBeansOfType(IMessageExchange.class).values();
    }
}
