package cn.eu.security;

import cn.eu.common.model.LoginUser;
import cn.eu.system.domain.SysUser;
import cn.hutool.core.util.StrUtil;

/**
 * @author Eu.z
 * @since 2024/7/14
 */
public class SecurityConvert {

    /**
     * 根据SysUser填充LoginUser
     */
    public static LoginUser fillLoginUserBySysUser(LoginUser loginUser, SysUser sysUser) {
        if (StrUtil.isNotBlank(sysUser.getId())) {
            loginUser.setUserId(sysUser.getId());
        }
        if (StrUtil.isNotBlank(sysUser.getUsername())) {
            loginUser.setUsername(sysUser.getUsername());
        }
        if (StrUtil.isNotBlank(sysUser.getNickname())) {
            loginUser.setNickname(sysUser.getNickname());
        }
        if (StrUtil.isNotBlank(sysUser.getAvatar())) {
            loginUser.setAvatar(sysUser.getAvatar());
        }
        if (StrUtil.isNotBlank(sysUser.getMobile())) {
            loginUser.setMobile(sysUser.getMobile());
        }
        if (StrUtil.isNotBlank(sysUser.getEmail())) {
            loginUser.setEmail(sysUser.getEmail());
        }
        if (sysUser.getSex() != null) {
            loginUser.setSex(sysUser.getSex());
        }
        if (sysUser.getAdmin() != null) {
            loginUser.setAdmin(sysUser.getAdmin());
        }
        if (sysUser.getDeptId() != null) {
            loginUser.setDeptId(sysUser.getDeptId());
        }
        return loginUser;
    }

}
