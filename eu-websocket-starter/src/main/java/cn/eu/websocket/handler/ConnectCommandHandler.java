package cn.eu.websocket.handler;

import cn.eu.websocket.enums.WsCommands;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author Eu.z
 * @since 2024/7/6
 */
@Slf4j
@Component
public class ConnectCommandHandler implements IWsCommandHandler<Void> {
    @Override
    public boolean support(int command) {
        return WsCommands.CONNECT.getCommand() == command;
    }

    @Override
    public void exchange(WebSocketSession session, Void data) {}
}
