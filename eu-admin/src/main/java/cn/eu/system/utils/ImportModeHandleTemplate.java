package cn.eu.system.utils;

import cn.eu.common.enums.ImportMode;
import cn.eu.system.model.dto.ImportResult;
import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 导入模式处理模板
 * @author zhaoeryu
 * @since 2023/8/25
 */
@Slf4j
public abstract class ImportModeHandleTemplate<T, K> {

    private final AtomicInteger addCount = new AtomicInteger(0);
    private final AtomicInteger updateCount = new AtomicInteger(0);
    private final Integer importMode;
    private final Function<T, K> primaryKey;

    public ImportModeHandleTemplate(Integer importMode, Function<T, K> primaryKey) {
        this.importMode = importMode;
        this.primaryKey = primaryKey;
    }

    /**
     * 处理excel数据
     * @param list excel数据
     */
    public final void handle(List<T> list) {
        // 校验数据
        valid(list);

        // 根据导入模式处理数据
        if (ImportMode.ONLY_ADD.getCode().equals(importMode)) {
            // 仅新增
            add(list);
            addCount.addAndGet(list.size());
            return;
        }

        // 把excel解析的数据转换成map
        ListIterator<T> iterator = list.listIterator(list.size());
        Map<K, T> excelMap = new HashMap<>();
        while (iterator.hasPrevious()) {
            T previous = iterator.previous();
            excelMap.put(primaryKey.apply(previous), previous);
        }
        // 从数据库查询已存在的数据
        List<T> dbList = getDbList(list);

        if (ImportMode.ONLY_UPDATE.getCode().equals(importMode)) {
            // 仅更新
            if (CollUtil.isEmpty(dbList)) {
                log.info("数据库没有匹配的数据，不进行处理");
                return;
            }

            // 设置更新的数据
            updateHandle(dbList, excelMap);
            return;
        } else if (ImportMode.ADD_AND_UPDATE.getCode().equals(importMode)) {
            // 新增和更新
            if (CollUtil.isNotEmpty(dbList)) {
                // 更新系统存在的数据
                updateHandle(dbList, excelMap);
            }

            // 不存在的数据，新增
            List<K> dbKeyList = dbList.stream().map(primaryKey).collect(Collectors.toList());
            List<T> needAddList = list.stream()
                    .filter(item -> !dbKeyList.contains(primaryKey.apply(item)))
                    .collect(Collectors.toList());
            if (CollUtil.isNotEmpty(needAddList)) {
                add(needAddList);
                addCount.addAndGet(needAddList.size());
            }
            return;
        }

        throw new IllegalArgumentException("导入模式错误");
    }

    /**
     * 获取处理结果
     */
    public ImportResult getResult() {
        ImportResult importResult = new ImportResult();
        importResult.setAddCount(addCount.get());
        importResult.setUpdateCount(updateCount.get());
        return importResult;
    }

    private void updateHandle(List<T> dbList, Map<K, T> excelMap) {
        List<T> needUpdateList = dbList.stream()
                .filter(dbItem -> excelMap.containsKey(primaryKey.apply(dbItem)))
                .map(dbItem -> {
                    T excelItem = excelMap.get(primaryKey.apply(dbItem));

                    return buildUpdateItem(dbItem, excelItem);
                }).collect(Collectors.toList());

        if (CollUtil.isNotEmpty(needUpdateList)) {
            update(needUpdateList);
            updateCount.addAndGet(needUpdateList.size());
        }
    }

    /**
     * 校验excel数据
     */
    protected abstract void valid(List<T> list);

    /**
     * 新增数据到数据库
     */
    public abstract void add(List<T> list);

    /**
     * 更新数据到数据库
     */
    public abstract void update(List<T> list);

    /**
     * 构建需要更新的字段
     * @param dbItem 数据库数据
     * @param excelItem excel数据
     */
    protected abstract T buildUpdateItem(T dbItem, T excelItem);

    /**
     * 获取数据库查询已存在的数据
     */
    protected abstract List<T> getDbList(List<T> list);
}
