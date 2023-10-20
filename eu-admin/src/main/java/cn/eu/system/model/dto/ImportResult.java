package cn.eu.system.model.dto;

import lombok.Data;

/**
 * 数据导入结果
 * @author zhaoeryu
 * @since 2023/8/23
 */
@Data
public class ImportResult {

    /**
     * 更新数据量
     */
    private Integer updateCount;

    /**
     * 新增数据量
     */
    private Integer addCount;

}
