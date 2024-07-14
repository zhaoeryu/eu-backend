package cn.eu.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.eu.common.annotation.Log;
import cn.eu.common.base.controller.EuBaseController;
import cn.eu.common.enums.BusinessType;
import cn.eu.common.model.PageResult;
import cn.eu.common.model.ResultBody;
import cn.eu.common.utils.EasyExcelHelper;
import cn.eu.common.utils.SpringContextHolder;
import cn.eu.event.LoginCacheRefreshEvent;
import cn.eu.common.utils.PasswordEncoder;
import cn.eu.system.domain.SysUser;
import cn.eu.system.model.dto.*;
import cn.eu.system.model.pojo.UpdatePasswordBody;
import cn.eu.system.model.query.AssignRoleQuery;
import cn.eu.system.model.query.SysUserQueryCriteria;
import cn.eu.system.service.ISysPostService;
import cn.eu.system.service.ISysRoleService;
import cn.eu.system.service.ISysUserService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author zhaoeryu
 * @since 2023/5/31
 */
@Slf4j
@RequestMapping("/api/system/user")
@RestController
public class SysUserController extends EuBaseController {

    @Autowired
    ISysUserService sysUserService;
    @Autowired
    ISysRoleService roleService;
    @Autowired
    ISysPostService postService;

    @Log(title = "查看用户列表", businessType = BusinessType.QUERY, isSaveResponseData = false)
    @SaCheckPermission("system:user:list")
    @GetMapping("/page")
    public ResultBody page(SysUserQueryCriteria criteria, @PageableDefault(page = 1) Pageable pageable) {
        return ResultBody.ok().data(sysUserService.page(criteria, pageable));
    }

    @Log(title = "新增用户", businessType = BusinessType.INSERT)
    @SaCheckPermission("system:user:add")
    @PostMapping
    public ResultBody save(@Validated @RequestBody SysUserDto dto) {
        String password = sysUserService.createUser(dto);
        return ResultBody.ok().data(password);
    }

    @Log(title = "编辑用户", businessType = BusinessType.UPDATE)
    @SaCheckPermission("system:user:edit")
    @PutMapping
    public ResultBody update(@Validated @RequestBody SysUserDto dto) {
        Assert.notNull(dto.getId(), "id不能为空");
        sysUserService.updateUser(dto);
        return ResultBody.ok();
    }

    @Log(title = "删除用户", businessType = BusinessType.DELETE)
    @SaCheckPermission("system:user:del")
    @DeleteMapping("/batch")
    public ResultBody batchDelete(@RequestBody List<String> ids) {
        Assert.notEmpty(ids, "id不能为空");
        sysUserService.removeByIds(ids);
        ids.forEach(id -> {
            // 更新缓存
            SpringContextHolder.getApplicationContext().publishEvent(new LoginCacheRefreshEvent(id));
        });
        return ResultBody.ok();
    }

    @Log(title = "导出用户", businessType = BusinessType.EXPORT, isSaveResponseData = false)
    @SaCheckPermission("system:user:export")
    @PostMapping("/export")
    public void export(SysUserQueryCriteria criteria, HttpServletResponse response) throws IOException {
        List<SysUser> list = sysUserService.list(criteria);

        EasyExcelHelper.export(response, list, SysUser.class);
    }

    @Log(title = "下载导入用户模版", businessType = BusinessType.EXPORT_TEMPLATE, isSaveResponseData = false)
    @SaCheckPermission("system:user:import")
    @PostMapping("/export-template")
    public void exportTemplate(HttpServletResponse response) throws IOException {
        sysUserService.exportTemplate(response);
    }

    @Log(title = "导入用户", businessType = BusinessType.IMPORT)
    @SaCheckPermission("system:user:import")
    @PostMapping("/import")
    public ResultBody importData(MultipartFile file, @RequestParam(value = "importMode", required = false) Integer importMode) throws IOException {
        return ResultBody.ok()
                .data(sysUserService.importData(file, importMode));
    }

    @Log(title = "修改用户状态", businessType = BusinessType.UPDATE)
    @SaCheckPermission("system:user:edit")
    @PutMapping("/updateStatus")
    public ResultBody updateStatus(@Validated @RequestBody UpdateUserStatusDto dto) {
        sysUserService.update(new LambdaUpdateWrapper<SysUser>()
            .eq(SysUser::getId, dto.getId())
            .set(SysUser::getStatus, dto.getStatus())
        );
        // 更新缓存
        SpringContextHolder.getApplicationContext().publishEvent(new LoginCacheRefreshEvent(dto.getId()));
        return ResultBody.ok();
    }

    @Log(title = "分配角色", businessType = BusinessType.UPDATE)
    @SaCheckPermission("system:user:edit")
    @PostMapping("/assignRole")
    public ResultBody assignRole(@RequestBody SysUserDto dto) {
        Assert.hasText(dto.getId(), "id不能为空");
        Assert.notEmpty(dto.getRoleIds(), "角色不能为空");
        sysUserService.assignRole(dto.getId(), dto.getRoleIds());
        return ResultBody.ok();
    }

    @Log(title = "更新个人信息", businessType = BusinessType.UPDATE)
    @PutMapping("/profile")
    public ResultBody updateProfile(@Validated @RequestBody SysUser entity) {
        Assert.notNull(entity.getId(), "id不能为空");
        sysUserService.update(new LambdaUpdateWrapper<SysUser>()
            .eq(SysUser::getId, entity.getId())
            .set(SysUser::getNickname, entity.getNickname())
            .set(SysUser::getMobile, entity.getMobile())
            .set(SysUser::getEmail, entity.getEmail())
            .set(SysUser::getSex, entity.getSex())
        );

        // 更新缓存
        SpringContextHolder.getApplicationContext().publishEvent(new LoginCacheRefreshEvent(entity.getId()));
        return ResultBody.ok();
    }

    @Log(title = "修改密码", businessType = BusinessType.UPDATE)
    @PutMapping("/update-pwd")
    public ResultBody updatePassword(@Validated @RequestBody UpdatePasswordBody updatePasswordBody) {
        String userId = StpUtil.getLoginIdAsString();
        SysUser user = sysUserService.getById(userId);

        Assert.isTrue(PasswordEncoder.matches(updatePasswordBody.getOldPassword(), user.getPassword()), "旧密码不正确");
        Assert.isTrue(!StrUtil.equals(updatePasswordBody.getOldPassword(), updatePasswordBody.getNewPassword()), "新密码不能与旧密码相同");

        sysUserService.update(new LambdaUpdateWrapper<SysUser>()
            .eq(SysUser::getId, userId)
            .set(SysUser::getPassword, PasswordEncoder.encode(updatePasswordBody.getNewPassword()))
            .set(SysUser::getPasswordResetTime, LocalDateTime.now())
        );

        // 更新缓存
        SpringContextHolder.getApplicationContext().publishEvent(new LoginCacheRefreshEvent(userId));
        // 退出重新登录
        StpUtil.logout();
        return ResultBody.ok();
    }

    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @SaCheckPermission("system:user:resetPwd")
    @PutMapping("/reset-pwd")
    public ResultBody updatePassword(@Validated @RequestBody ResetPasswordDto dto) {
        sysUserService.update(new LambdaUpdateWrapper<SysUser>()
            .eq(SysUser::getId, dto.getId())
            .set(SysUser::getPassword, PasswordEncoder.encode(dto.getPassword()))
            .set(SysUser::getPasswordResetTime, LocalDateTime.now())
        );
        // 登出
        StpUtil.logout(dto.getId());
        return ResultBody.ok();
    }

    @Log(title = "上传个人头像", businessType = BusinessType.UPDATE)
    @PostMapping("/upload-avatar")
    public ResultBody updateAvatar(@RequestParam("avatar") String avatar) {
        String userId = StpUtil.getLoginIdAsString();
        Assert.hasText(avatar, "头像不能为空");
        sysUserService.update(new LambdaUpdateWrapper<SysUser>()
            .eq(SysUser::getId, userId)
            .set(SysUser::getAvatar, avatar)
        );

        // 更新缓存
        SpringContextHolder.getApplicationContext().publishEvent(new LoginCacheRefreshEvent(userId));
        return ResultBody.ok();
    }

    @GetMapping("/info")
    public ResultBody getInfo(@RequestParam("id") String id) {
        Assert.hasText(id, "id不能为空");

        SysUser sysUser = sysUserService.getById(id);
        sysUser.setPassword(null);

        List<Integer> roleIds = roleService.getRoleIdsByUserId(id);
        List<Integer> postIds = postService.getPostIdsByUserId(id);

        Map<String, Object> respMap = new HashMap<>();
        respMap.put("user", sysUser);
        respMap.put("postIds", postIds);
        respMap.put("roleIds", roleIds);

        return ResultBody.ok().data(respMap);
    }

    @SaCheckPermission("system:role:assign")
    @GetMapping("/roleUserList")
    public ResultBody roleUserList(@Validated AssignRoleQuery query,
                                   @PageableDefault(page = 1) Pageable pageable) {
        PageResult<SysUser> result = sysUserService.roleUserList(query, pageable);
        return ResultBody.ok().data(result);
    }

    @Log(title = "取消授权", businessType = BusinessType.DELETE)
    @SaCheckPermission("system:user:edit")
    @DeleteMapping("/cancelAuth")
    public ResultBody cancelAuth(@Validated @RequestBody AssignRoleDto dto) {
        sysUserService.cancelAuth(dto);
        return ResultBody.ok();
    }

    @Log(title = "批量给用户授权角色", businessType = BusinessType.INSERT)
    @SaCheckPermission("system:user:edit")
    @PostMapping("/batchAssignRole")
    public ResultBody batchAssignRole(@Validated @RequestBody AssignRoleDto dto) {
        sysUserService.batchAssignRole(dto);
        return ResultBody.ok();
    }
}
