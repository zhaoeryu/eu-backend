package cn.eu.common.core.service.impl;

import cn.eu.common.core.service.IEuService;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author zhaoeryu
 * @since 2023/5/31
 */
public abstract class EuServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements IEuService<T> {

    /**
     * 分页参数从1开始
     */
    protected <E> Page<E> getPage(Pageable pageable) {
        return getPage(pageable, true);
    }

    /**
     * 分页参数从1开始
     */
    protected <E> Page<E> getPage(Pageable pageable, boolean isOrder) {
//        // 注意：Spring Data 的页码从 0 开始，MyBatis-Plus 从 1 开始
//        Page<E> page = Page.of(pageable.getPageNumber(), pageable.getPageSize());
//        if (isOrder) {
//            Sort sort = pageable.getSort();
//            for (Sort.Order order : sort) {
//                OrderItem orderItem = new OrderItem();
//                orderItem.setColumn(order.getProperty());
//                orderItem.setAsc(order.isAscending());
//                page.addOrder(orderItem);
//            }
//        }
//        return page;
        if (isOrder) {
            String order = null;
            if (pageable.getSort() != null) {
                order = pageable.getSort().toString();
                order = order.replace(":", " ");
                if ("UNSORTED".equals(order)) {
                    return PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
                }
            }
            return PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize(), order);
        }
        return PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
    }
}
