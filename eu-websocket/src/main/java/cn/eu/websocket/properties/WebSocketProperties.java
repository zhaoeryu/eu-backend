package cn.eu.websocket.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Eu.z
 * @since 2024/7/6
 */
@Data
@ConfigurationProperties(prefix = "eu.websocket")
public class WebSocketProperties {

    /**
     * WebSocket path
     */
    private String path = "/ws";

    /**
     * WebSocket建立连接时的token参数名
     */
    private String tokenFieldKey = "token";

    /**
     * WebSocket 空闲检测时间(ms)，超过该时间未收到客户端消息则断开连接
     */
    private long idleTime = 2 * 60 * 1000;

}
