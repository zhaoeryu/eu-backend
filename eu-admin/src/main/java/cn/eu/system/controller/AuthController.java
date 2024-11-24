package cn.eu.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.eu.common.annotation.Log;
import cn.eu.common.constants.Constants;
import cn.eu.common.enums.BusinessType;
import cn.eu.common.model.PageResult;
import cn.eu.common.model.ResultBody;
import cn.eu.common.utils.LoginUtil;
import cn.eu.common.utils.RedisUtil;
import cn.eu.common.model.LoginUser;
import cn.eu.common.model.LoginBody;
import cn.eu.security.model.OnlineUserVo;
import cn.eu.security.service.LoginService;
import cn.eu.system.domain.SysUser;
import cn.eu.system.service.ISysUserService;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.wf.captcha.ArithmeticCaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author zhaoeryu
 * @since 2023/6/3
 */
@Slf4j
@RequestMapping("/api/auth")
@RestController
public class AuthController {

    @Autowired
    LoginService loginService;
    @Autowired
    ISysUserService sysUserService;
    @Autowired
    RedisUtil redisUtil;

    @Log(title = "登录", businessType = BusinessType.LOGIN, isSaveResponseData = false, excludeParamNames = { "password" })
    @SaIgnore
    @PostMapping("/login")
    public ResultBody login(@Validated @RequestBody LoginBody loginBody) {
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getUuid(), loginBody.getVerifyCode());
        return ResultBody.ok().data(token);
    }

    @GetMapping("/info")
    public ResultBody info() {
        LoginUser user = LoginUtil.getLoginUser();
        List<String> permissions = StpUtil.getPermissionList();
        List<String> roles = StpUtil.getRoleList();

        Map<String, Object> respMap = new HashMap<>();
        respMap.put("user", user);
        respMap.put("permissions", permissions);
        respMap.put("roles", roles);

        // 更新用户活跃时间
        LambdaUpdateWrapper<SysUser> updateWrapper = new LambdaUpdateWrapper<SysUser>()
                .eq(SysUser::getId, StpUtil.getLoginIdAsString())
                .set(SysUser::getLastActiveTime, LocalDateTime.now());
        sysUserService.update(updateWrapper);

        return ResultBody.ok().data(respMap);
    }

    @Log(title = "登出", businessType = BusinessType.LOGOUT)
    @SaIgnore
    @PostMapping("/logout")
    public ResultBody logout() {
        StpUtil.logout();
        return ResultBody.ok();
    }

    @SaIgnore
    @GetMapping("/captcha")
    public ResultBody captcha() {

        String uuid = IdUtil.fastSimpleUUID();

        // 算术类型
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(150, 38);
        // 几位数运算，默认是两位
        captcha.setLen(2);
        // 获取运算的公式：3+2=?
        captcha.getArithmeticString();
        // 获取运算的结果：5
        captcha.text();

        // 保存验证码到redis
        redisUtil.setEx(Constants.CAPTCHA_REDIS_KEY + uuid, captcha.text(), Constants.CAPTCHA_EXPIRATION, TimeUnit.SECONDS);
        log.info("uuid: {}, captcha: {}", uuid, captcha.text());

        return ResultBody.ok()
                .putValue("uuid", uuid)
                .putValue("img", captcha.toBase64());
    }

    /**
     * 在线用户列表
     */
    @Log(title = "查看在线用户列表", businessType = BusinessType.QUERY, isSaveResponseData = false)
    @SaCheckPermission("monitor:online:list")
    @GetMapping("/online")
    public ResultBody online(@RequestParam(required = false, value = "nickname") String nickname, @PageableDefault(page = 1) Pageable pageable) {
        // 获取所有已登录的会话id(如果系统使用的用户比较多，此处会有内存压力，这些限制最多99条数据)
        List<String> sessionIdList = StpUtil.searchSessionId("", 0, 99, true);

        List<OnlineUserVo> list = new ArrayList<>();
        for (String sessionId : sessionIdList) {

            // 根据会话id，查询对应的 SaSession 对象，此处一个 SaSession 对象即代表一个登录的账号
            SaSession session = StpUtil.getSessionBySessionId(sessionId);

            LoginUser authUser = LoginUtil.getLoginUserBySaSession(session);
            OnlineUserVo onlineUserVo = new OnlineUserVo();
            onlineUserVo.setId(authUser.getUserId());
            onlineUserVo.setUsername(authUser.getUsername());
            onlineUserVo.setNickname(authUser.getNickname());
            onlineUserVo.setDeptName(authUser.getDeptName());
            onlineUserVo.setLoginIp(authUser.getLoginIp());
            onlineUserVo.setLoginRegion(authUser.getLoginRegion());
            onlineUserVo.setLoginTime(authUser.getLoginTime());
            onlineUserVo.setBrowser(authUser.getBrowser());
            onlineUserVo.setOs(authUser.getOs());
            list.add(onlineUserVo);
        }

        // 筛选数据
        if (StrUtil.isNotBlank(nickname)) {
            list = list.stream().filter(user -> user.getNickname().contains(nickname)).collect(Collectors.toList());
        }

        // 分页处理
        long total = list.size();

        int fromIndex = (pageable.getPageNumber() - 1) * pageable.getPageSize();
        int toIndex = (pageable.getPageNumber() - 1) * pageable.getPageSize() + pageable.getPageSize();
        if (fromIndex > total) {
            list = new ArrayList<>();
        } else if (toIndex >= total) {
            list = list.subList(fromIndex, (int)total);
        } else {
            list = list.subList(fromIndex, toIndex);
        }

        return ResultBody.ok().data(PageResult.of(list, total));
    }

    @Log(title = "踢人下线", businessType = BusinessType.KICKOUT)
    @SaCheckPermission("monitor:online:kickout")
    @PostMapping("/online/kickout")
    public ResultBody kickout(@RequestParam("userId") String userId) {
        StpUtil.kickout(userId);
        return ResultBody.ok();
    }

    @Log(title = "强制注销", businessType = BusinessType.FORCE_LOGOUT)
    @SaCheckPermission("monitor:online:logout")
    @PostMapping("/online/logout")
    public ResultBody logout(@RequestParam("userId") String userId) {
        StpUtil.logout(userId);
        return ResultBody.ok();
    }

}
