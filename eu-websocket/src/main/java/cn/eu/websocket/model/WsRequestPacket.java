package cn.eu.websocket.model;

import lombok.Data;

/**
 * @author Eu.z
 * @since 2024/7/6
 */
@Data
public class WsRequestPacket {

    private Integer cmd;
    private String data;

}
