package cn.eu.event.listener;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.eu.common.constants.Constants;
import cn.eu.common.enums.SysUserStatus;
import cn.eu.event.LoginCacheRefreshEvent;
import cn.eu.common.utils.LoginUtil;
import cn.eu.common.model.LoginUser;
import cn.eu.security.SecurityConvert;
import cn.eu.system.domain.SysRole;
import cn.eu.system.domain.SysUser;
import cn.eu.system.service.ISysDeptService;
import cn.eu.system.service.ISysRoleService;
import cn.eu.system.service.ISysUserService;
import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 刷新登录缓存事件监听器
 * @author zhaoeryu
 * @since 2023/8/8
 */
@Slf4j
@Component
public class LoginCacheRefreshListener implements ApplicationListener<LoginCacheRefreshEvent> {

    @Autowired
    ISysRoleService roleService;
    @Autowired
    ISysUserService userService;
    @Autowired
    ISysDeptService sysDeptService;

    @Async
    @Override
    public void onApplicationEvent(LoginCacheRefreshEvent event) {
        String userId = (String) event.getSource();
        log.debug("刷新登录缓存 {}", userId);
        Assert.hasLength(userId, "userId为空");

        // 修改个人信息：/user/profile
        // 修改密码：/user/update-pwd
        // 上传头像：/user/upload-avatar

        // 修改用户信息：/user  [PUT]
        // 重置密码：/user/reset-pwd
        // 删除用户：/user [DELETE]
        // 分配角色：/user/assignRole
        // 取消角色授权： /user/cancelAuth
        // 批量给用户授权角色：/user/batchAssignRole

        SaSession session = StpUtil.getSessionByLoginId(userId, false);
        if (session == null) {
            // 该用户没有登录，不用刷新缓存
            return;
        }
        Object userStr = session.get(Constants.USER_KEY);
        LoginUser authUser = JSONObject.parseObject(userStr.toString(), LoginUser.class);


        SysUser sysUser = userService.getById(userId);
        if (sysUser == null) {
            // 没有在系统里找到该用户，可能已被删除，强制退出
            StpUtil.logout(userId);
            return;
        }

        // 判断该用户的状态，如果非正常状态，则进行强制退出
        if (!SysUserStatus.isNormal(sysUser.getStatus())) {
            StpUtil.logout(userId);
            return;
        }

        // 重新加载角色
        List<SysRole> roles = roleService.getRolesByUserId(userId);
        session.set(Constants.ROLE_KEY, roles);

        // 查询用户信息，重新设置登录缓存 or 直接从event.source中获取用户信息进行更新
        authUser = SecurityConvert.fillLoginUserBySysUser(authUser, sysUser);

        // 部门
        String deptName = null;
        List<String> deptNames = null;
        if (sysUser.getDeptId() != null) {
            deptNames = sysDeptService.getParentDeptNames(sysUser.getDeptId());
            if (CollUtil.isNotEmpty(deptNames)) {
                deptName = deptNames.get(deptNames.size() - 1);
            }
        }
        authUser.setDeptName(deptName);
        authUser.setDeptNames(deptNames);

        session.set(Constants.USER_KEY, authUser);
    }
}
