package cn.eu.websocket.utils;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.eu.common.constants.Constants;
import cn.eu.common.model.AuthUser;
import cn.eu.websocket.model.WsResponsePacket;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Eu.z
 * @since 2024/7/6
 */
@Slf4j
public class WsSessionUtil {

    private static final ConcurrentHashMap<String, WebSocketSession> tokenSessionMap = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, List<WebSocketSession>> userIdSessionMap = new ConcurrentHashMap<>();

    public static void bindSession(WebSocketSession session){
        String token = getToken(session).orElseThrow(() -> new IllegalArgumentException("token 不能为空"));
        String userId = getUserId(session).orElseThrow(() -> new IllegalArgumentException("用户未登录"));

        tokenSessionMap.putIfAbsent(token, session);
        userIdSessionMap.computeIfAbsent(userId, k -> new ArrayList<>()).add(session);
    }

    public static void unbindSession(WebSocketSession session){
        String token = getToken(session).orElseThrow(() -> new IllegalArgumentException("token 不能为空"));
        String userId = getUserId(session).orElseThrow(() -> new IllegalArgumentException("用户未登录"));

        tokenSessionMap.remove(token);
        userIdSessionMap.get(userId).remove(session);
    }

    public static Optional<String> getToken(WebSocketSession session){
        return Optional.ofNullable(session.getAttributes().get(Constants.WS_SESSION_TOKEN_KEY))
                .map(Object::toString);
    }
    public static Optional<String> getUserId(WebSocketSession session){
        return Optional.ofNullable(session.getAttributes().get(Constants.WS_SESSION_USER_ID_KEY))
                .map(Object::toString);
    }

    public static void bindCmd(WebSocketSession session, int cmd){
        session.getAttributes().put(Constants.WS_SESSION_CMD_KEY, cmd);
    }

    public static Integer getCmd(WebSocketSession session){
        return (Integer) session.getAttributes().get(Constants.WS_SESSION_CMD_KEY);
    }

    public static Optional<AuthUser> getLoginUserByWsSession(WebSocketSession session) {
        return Optional.ofNullable(session.getAttributes().get(Constants.WS_SESSION_TOKEN_KEY))
                .map(Object::toString)
                .map(StpUtil::getLoginIdByToken)
                .map(StpUtil::getSessionByLoginId)
                .map(SaSession::getDataMap)
                .map(dataMap -> ((JSONObject) dataMap.get(Constants.USER_KEY)))
                .map(jsonObject -> jsonObject.toJavaObject(AuthUser.class));
    }

    public static void sendMessage(String token, Integer cmd, Object data) {
        Optional.ofNullable(tokenSessionMap.get(token))
                .ifPresent(session -> sendMessage(session, cmd, data));
    }

    public static void broadcastToUser(String userId, Integer cmd, Object data) {
        Optional.ofNullable(userIdSessionMap.get(userId))
                .ifPresent(sessions -> sessions.forEach(session -> sendMessage(session, cmd, data)));
    }

    public static void resp(WebSocketSession session, Object data) {
        sendMessage(session, getCmd(session), data);
    }
    public static void sendMessage(WebSocketSession session, Integer cmd, Object data) {
        try {
            WsResponsePacket packet = new WsResponsePacket(data);
            if (cmd != null) {
                packet.setCmd(cmd);
            }
            session.sendMessage(new TextMessage(JSONObject.toJSONString(packet)));
        } catch (IOException e) {
            log.error("发送消息失败", e);
        }
    }

}
