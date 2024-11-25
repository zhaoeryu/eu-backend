package cn.eu.system.mapper;

import cn.eu.common.core.mapper.EuBaseMapper;
import cn.eu.system.domain.SysDept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/5/31
 */
@Mapper
public interface SysDeptMapper extends EuBaseMapper<SysDept> {

    /**
     * 根据部门ID查询角色和部门关联的数量
     */
    int selectRoleDeptCountByDeptIds(@Param("deptIds") List<Integer> deptIds);

}
