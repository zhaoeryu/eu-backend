package cn.eu.websocket;

import cn.eu.common.utils.SpringContextHolder;
import cn.eu.websocket.enums.WsCommands;
import cn.eu.websocket.model.WsRequestPacket;
import cn.eu.websocket.utils.WsSessionUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

/**
 * @author zhaoeryu
 */
@Slf4j
public class MyWebSocketHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        WsRequestPacket requestPacket;
        try {
            requestPacket = JSONObject.parseObject(message.getPayload(), WsRequestPacket.class);
        } catch (Exception e) {
            WsSessionUtil.sendMessage(session, null, "非法数据体");
            return;
        }

        // 校验指令是否合法
        if (!WsCommands.isExist(requestPacket.getCmd())) {
            WsSessionUtil.sendMessage(session, requestPacket.getCmd(), "非法指令");
            return;
        }

        // 绑定指令
        WsSessionUtil.bindCmd(session, requestPacket.getCmd());

        try {
            // 分发指令处理逻辑
            SpringContextHolder.getBean(WsCommandDispatcher.class).dispatch(session, requestPacket);
        } catch (Exception e) {
            log.error("处理指令异常", e);
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        WsSessionUtil.bindSession(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        WsSessionUtil.unbindSession(session);
    }

}