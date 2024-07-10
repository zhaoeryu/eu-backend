package cn.eu.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/6/4
 */
@Data
public class AuthUser {

    private String userId;
    private String username;
    private String nickname;
    private String avatar;
    private String mobile;
    private String email;
    private Integer sex;
    private Integer admin;
    private String loginIp;
    private String loginRegion;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginTime;
    private String prevLoginIp;
    private String prevLoginRegion;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime prevLoginTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private Integer deptId;
    private String deptName;
    /** 上海分/研发部 */
    private List<String> deptNames;
    private String browser;
    private String os;

}
