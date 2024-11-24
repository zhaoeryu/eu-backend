package cn.eu.websocket;

import cn.eu.websocket.handler.IWsCommandHandler;
import cn.eu.websocket.model.WsRequestPacket;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.Collection;

/**
 * @author Eu.z
 * @since 2024/7/6
 */
@Component
public class WsCommandDispatcher implements ApplicationContextAware {

    private Collection<IWsCommandHandler> commandHandlers;

    public void dispatch(WebSocketSession session, WsRequestPacket packet) {
        commandHandlers.stream()
                .filter(handler -> handler.support(packet.getCmd()))
                .forEach(handler -> handler.exchange(session, packet.getData()));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        commandHandlers = applicationContext.getBeansOfType(IWsCommandHandler.class).values();
    }
}
