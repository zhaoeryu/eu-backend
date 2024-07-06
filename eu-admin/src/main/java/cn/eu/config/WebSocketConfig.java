package cn.eu.config;

import cn.eu.websocket.MyHandshakeInterceptor;
import cn.eu.websocket.MyWebSocketHandler;
import cn.eu.websocket.properties.WebSocketProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

import javax.annotation.Resource;

@Configuration
public class WebSocketConfig implements WebSocketConfigurer {

    @Resource
    MyHandshakeInterceptor myHandshakeInterceptor;
    @Resource
    WebSocketProperties webSocketProperties;
    
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry
                //添加myHandler消息处理对象以及websocket连接地址
                .addHandler(myWebSocketHandler(), webSocketProperties.getPath())
                //设置允许跨域访问
                .setAllowedOrigins("*")
                //添加拦截器可实现用户链接前进行权限校验等操作
                .addInterceptors(myHandshakeInterceptor);
    }

    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxSessionIdleTimeout(webSocketProperties.getIdleTime());
        return container;
    }

    @Bean
    public WebSocketHandler myWebSocketHandler() {
        return new MyWebSocketHandler();
    }
}