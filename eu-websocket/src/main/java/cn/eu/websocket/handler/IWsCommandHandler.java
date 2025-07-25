package cn.eu.websocket.handler;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.web.socket.WebSocketSession;

import java.lang.reflect.ParameterizedType;

/**
 * @author Eu.z
 * @since 2024/7/6
 */
public interface IWsCommandHandler<T> {

    boolean support(int command);

    default void exchange(WebSocketSession session, String data) {
        T t = null;
        try {
            Class<T> clazz = (Class<T>) ((ParameterizedType)getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
            if (clazz != Void.class) {
                t = JSONObject.parseObject(data, clazz);
            }
        } catch (Exception e) {
            throw new RuntimeException("数据异常：" + e.getMessage());
        }
        exchange(session, t);
    }

    void exchange(WebSocketSession session, T data);

}
