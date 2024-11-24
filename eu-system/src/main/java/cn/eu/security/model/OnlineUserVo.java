package cn.eu.security.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhaoeryu
 * @since 2023/6/11
 */
@Data
public class OnlineUserVo {

    private String id;
    private String username;
    private String nickname;
    private String deptName;
    private String loginIp;
    private String loginRegion;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginTime;
    private String browser;
    private String os;

}
