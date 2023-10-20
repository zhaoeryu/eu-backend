package cn.eu.system.mapper;

import cn.eu.common.base.mapper.EuBaseMapper;
import cn.eu.system.domain.SysPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/5/31
 */
@Mapper
public interface SysPostMapper extends EuBaseMapper<SysPost> {
    List<SysPost> getPostsByUserId(@Param("userId") String userId, @Param("status") Integer status);
}
