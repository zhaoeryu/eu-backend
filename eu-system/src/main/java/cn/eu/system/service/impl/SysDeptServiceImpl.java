package cn.eu.system.service.impl;

import cn.eu.common.annotation.DataScope;
import cn.eu.common.core.service.impl.EuServiceImpl;
import cn.eu.common.enums.EnableFlag;
import cn.eu.common.utils.MessageUtils;
import cn.eu.common.utils.MpQueryHelper;
import cn.eu.system.domain.SysDept;
import cn.eu.system.mapper.SysDeptMapper;
import cn.eu.system.model.query.SysDeptQueryCriteria;
import cn.eu.system.service.ISysDeptService;
import cn.eu.system.service.ISysUserService;
import cn.eu.system.utils.DeptNameConcatenater;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author zhaoeryu
 * @since 2023/5/31
 */
@Service
public class SysDeptServiceImpl extends EuServiceImpl<SysDeptMapper, SysDept> implements ISysDeptService {

    @Autowired
    SysDeptMapper sysDeptMapper;
    @Lazy
    @Autowired
    ISysUserService sysUserService;

    @DataScope(isSingleQuery = true)
    @Override
    public List<SysDept> list(SysDeptQueryCriteria criteria) {
        return list(MpQueryHelper.buildQueryWrapper(criteria, SysDept.class));
    }

    @Override
    public List<String> getParentDeptNames(Integer deptId) {
        // 获取所有部门并转换成数节点
        List<TreeNode<Integer>> nodes = list().stream().map(item -> {
            return new TreeNode<>(item.getId(), item.getParentId(), item.getDeptName(), Optional.ofNullable(item.getSortNum()).orElse(0));
        }).collect(Collectors.toList());
        // 构建树
        List<Tree<Integer>> trees = TreeUtil.build(nodes, 0);
        // 获取指定节点的所有上级节点名称
        List<String> parentNames = Optional.ofNullable(trees).orElse(CollUtil.newArrayList()).stream().map(item -> {
            // 获取指定节点
            Tree<Integer> node = TreeUtil.getNode(item, deptId);
            if (node != null) {
                // 获取指定节点的所有上级节点名称
                List<String> names = node.getParentsName(true).stream()
                        .filter(Objects::nonNull)
                        .map(String::valueOf)
                        .collect(Collectors.toList());
                // 反转列表
                Collections.reverse(names);
                return names;
            }
            return null;
        }).filter(Objects::nonNull).findFirst()
        .orElse(CollUtil.newArrayList());
        return parentNames;
    }

    @Override
    public void checkCanDelete(List<Integer> deptIds) {
        // 检查是否关联用户
        Assert.isTrue(sysUserService.countByDeptIds(deptIds) == 0, MessageUtils.message("assert.SysDept.existsConnection"));

        // 检查是否关联数据权限
        Assert.isTrue(sysDeptMapper.selectRoleDeptCountByDeptIds(deptIds) == 0, MessageUtils.message("assert.SysDept.withRoleExistsConnection"));
    }

    @DataScope(isSingleQuery = true)
    @Override
    public List<SysDept> listWithConcatenatedDeptName() {
        return DeptNameConcatenater.concatenater(list(new LambdaQueryWrapper<SysDept>()
                .eq(SysDept::getStatus, EnableFlag.ENABLED)
                .orderByAsc(SysDept::getParentIds)
                .orderByAsc(SysDept::getSortNum)
        ));
    }
}
