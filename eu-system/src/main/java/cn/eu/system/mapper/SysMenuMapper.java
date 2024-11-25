package cn.eu.system.mapper;

import cn.eu.common.core.mapper.EuBaseMapper;
import cn.eu.system.domain.SysMenu;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/5/31
 */
@Mapper
public interface SysMenuMapper extends EuBaseMapper<SysMenu> {
    List<SysMenu> getMenuPermissionByUserId(@Param("userId") String userId, @Param("status") Integer status);

    List<SysMenu> getMenusByUserId(@Param("userId") String userId, @Param("status") Integer status, @Param("menuType") Integer menuType);

    List<SysMenu> selectMenus(@Param(Constants.WRAPPER) Wrapper<SysMenu> wrapper, @Param("userId") String userId);
}
