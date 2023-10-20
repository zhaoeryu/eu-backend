package cn.eu.message.handler;

import cn.eu.common.model.Message;

/**
 * @author zhaoeryu
 * @since 2023/6/10
 */
public interface IMessageExchange {

    boolean support(Message message);

    void exchange(Message message);
}
