package cn.eu.websocket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Eu.z
 * @since 2024/7/6
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WsResponsePacket {

    private Integer cmd;
    private Object data;

    public WsResponsePacket(Object data) {
        this.data = data;
    }
}
