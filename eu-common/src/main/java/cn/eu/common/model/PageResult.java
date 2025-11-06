package cn.eu.common.model;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/6/2
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {

    private List<T> records;
    private Long total;

    public static <T> PageResult<T> of(List<T> records, Long total) {
        return new PageResult<>(records, total);
    }

    public static <T> PageResult<T> of(IPage<T> page) {
        return of(page.getRecords(), page.getTotal());
    }
    public static <T> PageResult<T> of(List<T> records) {
        PageInfo<T> pageInfo = new PageInfo<>(records);
        return of(pageInfo.getList(), pageInfo.getTotal());
    }

}
