package cn.eu.test;

import cn.eu.system.domain.SysDept;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.alibaba.fastjson.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhaoeryu
 * @since 2023/6/5
 */
public class BootTest {

    public static void main(String[] args) {
        String json = FileUtil.readString("/Users/zhaoeryu/Desktop/无标题.json" , "utf-8");
        List<TreeNode<Integer>> nodes = JSONObject.parseObject(json).getJSONArray("RECORDS").toJavaList(SysDept.class).stream()
                .map(item -> {
                    return new TreeNode<>(item.getId(), item.getParentId(), item.getDeptName(), Optional.ofNullable(item.getSortNum()).orElse(0));
                }).collect(Collectors.toList());
        List<Tree<Integer>> trees = TreeUtil.build(nodes, null);
        trees.forEach(tree -> {
            Tree<Integer> node = TreeUtil.getNode(tree, 9);
            if (node != null) {
                List<String> ids = node.getParentsName(true).stream()
                        .filter(Objects::nonNull)
                        .map(String::valueOf)
                        .collect(Collectors.toList());
                // 反转ids
                Collections.reverse(ids);
                System.out.println(String.join("_", ids));
            }
        });
    }

}
