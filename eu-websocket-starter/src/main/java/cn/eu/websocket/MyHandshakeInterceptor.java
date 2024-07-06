package cn.eu.websocket;

import cn.dev33.satoken.stp.StpUtil;
import cn.eu.common.constants.Constants;
import cn.eu.websocket.properties.WebSocketProperties;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Slf4j
@Component
public class MyHandshakeInterceptor implements HandshakeInterceptor {

    @Autowired
    WebSocketProperties webSocketProperties;

    /**
     * 握手之前，若返回false，则不建立链接
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse
            response, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        // 开始握手
        if (!(request instanceof ServletServerHttpRequest)) {
            return false;
        }

        ServletServerHttpRequest serverHttpRequest = (ServletServerHttpRequest) request;
        String token = serverHttpRequest.getServletRequest().getParameter(webSocketProperties.getTokenFieldKey());
        if (StrUtil.isBlank(token)) {
            return false;
        }

        // 验证token是否登录
        Object loginId = StpUtil.getLoginIdByToken(token);
        if (loginId == null) {
            return false;
        }
        attributes.put(Constants.WS_SESSION_TOKEN_KEY, token);
        attributes.put(Constants.WS_SESSION_USER_ID_KEY, loginId);
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse
            response, WebSocketHandler wsHandler, Exception exception) {
        // 握手成功

    }
}