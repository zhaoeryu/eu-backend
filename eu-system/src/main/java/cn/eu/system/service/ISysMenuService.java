package cn.eu.system.service;

import cn.eu.common.base.service.IEuService;
import cn.eu.system.domain.SysMenu;
import cn.eu.system.model.query.SysMenuQueryCriteria;

import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/5/31
 */
public interface ISysMenuService extends IEuService<SysMenu> {

    List<SysMenu> list(SysMenuQueryCriteria criteria);

    /**
     * 获取用户拥有的菜单权限
     * @param userId
     * @return
     */
    List<String> getMenuPermissionByUserId(String userId);

    /**
     * 获取用户拥有的菜单(不包含按钮)
     * @param userId 用户id
     * @return 所拥有的菜单列表
     */
    List<SysMenu> getMenusByUserId(String userId);
}
