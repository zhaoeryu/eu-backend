package cn.eu.websocket.autoconfigure;

import cn.eu.websocket.properties.WebSocketProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * @author Eu.z
 * @since 2024/7/6
 */
@Configuration
@EnableConfigurationProperties({WebSocketProperties.class})
@EnableWebSocket
public class EuWebSocketAutoConfiguration {
}
