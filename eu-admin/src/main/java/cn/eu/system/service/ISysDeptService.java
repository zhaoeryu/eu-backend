package cn.eu.system.service;

import cn.eu.common.base.service.IEuService;
import cn.eu.common.model.PageResult;
import cn.eu.system.domain.SysDept;
import cn.eu.system.model.query.SysDeptQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/5/31
 */
public interface ISysDeptService extends IEuService<SysDept> {

    List<SysDept> list(SysDeptQueryCriteria criteria);

    /**
     * 获取部门的所有上级部门名称
     * @param deptId 部门id
     * @return 上级部门名称列表
     */
    List<String> getParentDeptNames(Integer deptId);

    /**
     * 检查部门是否可删除
     */
    void checkCanDelete(List<Integer> deptIds);

    /**
     * 查询系统里的部门，并按照部门层级拼接部门名称
     */
    List<SysDept> listWithConcatenatedDeptName();
}
