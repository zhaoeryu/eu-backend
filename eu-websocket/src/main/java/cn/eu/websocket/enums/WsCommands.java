package cn.eu.websocket.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Eu.z
 * @since 2024/7/6
 */
@AllArgsConstructor
@Getter
public enum WsCommands {

    // 连接
    CONNECT(1),
    // 心跳
    KEEPALIVE(2);

    private final int command;


    /**
     * 指令是否存在
     */
    public static boolean isExist(int command) {
        for (WsCommands value : WsCommands.values()) {
            if (value.getCommand() == command) {
                return true;
            }
        }
        return false;
    }
}
