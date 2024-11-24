package cn.eu.system.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.eu.common.base.service.impl.EuServiceImpl;
import cn.eu.common.enums.MenuStatus;
import cn.eu.common.enums.MenuType;
import cn.eu.common.utils.MpQueryHelper;
import cn.eu.common.utils.LoginUtil;
import cn.eu.system.domain.SysMenu;
import cn.eu.system.mapper.SysMenuMapper;
import cn.eu.system.model.query.SysMenuQueryCriteria;
import cn.eu.system.service.ISysMenuService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhaoeryu
 * @since 2023/5/31
 */
@Service
public class SysMenuServiceImpl extends EuServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Autowired
    SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> list(SysMenuQueryCriteria criteria) {
        criteria.setStatus(MenuStatus.NORMAL.getValue());
        if (LoginUtil.isAdminLogin()) {
            return list(MpQueryHelper.buildQueryWrapper(criteria, SysMenu.class)
                    .lambda()
                    .orderByAsc(SysMenu::getParentId, SysMenu::getSortNum));
        }
        QueryWrapper<SysMenu> queryWrapper = MpQueryHelper.buildQueryWrapperWithDelFlag(criteria, SysMenu.class, "m");
        return sysMenuMapper.selectMenus(queryWrapper, StpUtil.getLoginIdAsString());
    }

    @Override
    public List<String> getMenuPermissionByUserId(String userId) {
        List<SysMenu> menus = sysMenuMapper.getMenuPermissionByUserId(userId, MenuStatus.NORMAL.getValue());
        return menus.stream()
                .map(SysMenu::getPermission)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<SysMenu> getMenusByUserId(String userId) {
        if (LoginUtil.isAdminLogin()) {
            // 管理员登录，返回所有菜单
            LambdaQueryWrapper<SysMenu> queryWrapper = new LambdaQueryWrapper<SysMenu>()
                    .eq(SysMenu::getStatus, MenuStatus.NORMAL.getValue())
                    .ne(SysMenu::getMenuType, MenuType.BUTTON.getValue());
            return list(queryWrapper);
        }
        return sysMenuMapper.getMenusByUserId(userId, MenuStatus.NORMAL.getValue(), MenuType.BUTTON.getValue());
    }
}
