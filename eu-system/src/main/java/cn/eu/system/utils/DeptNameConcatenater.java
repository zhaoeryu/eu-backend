package cn.eu.system.utils;

import cn.eu.system.domain.SysDept;

import java.util.*;

/**
 * @author zhaoeryu
 */
public class DeptNameConcatenater {

    public static List<SysDept> concatenater(List<SysDept> departments) {
        Map<Integer, SysDept> deptMap = new HashMap<>();
        departments.forEach(dept -> deptMap.put(dept.getId(), dept));

        List<SysDept> newDeptList = new ArrayList<>();

        for (SysDept dept : departments) {
            String[] parentIds = dept.getParentIds().split(",");
            StringJoiner stringJoiner = new StringJoiner("/");
            for (String parentId : parentIds) {
                if ("0".equals(parentId)) {
                    continue;
                }
                SysDept parentDept = deptMap.get(Integer.parseInt(parentId));
                if (parentDept != null) {
                    stringJoiner.add(parentDept.getDeptName());
                }
            }
            stringJoiner.add(dept.getDeptName());

            SysDept sysDept = new SysDept();
            sysDept.setId(dept.getId());
            sysDept.setDeptName(stringJoiner.toString());
            newDeptList.add(sysDept);
        }

        return newDeptList;
    }
}