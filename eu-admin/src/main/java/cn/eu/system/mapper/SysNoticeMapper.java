package cn.eu.system.mapper;

import cn.eu.common.base.mapper.EuBaseMapper;
import cn.eu.system.domain.SysNotice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 通知公告 Mapper
 * @author Eu.z
 * @since 2023/08/30
 */
@Mapper
public interface SysNoticeMapper extends EuBaseMapper<SysNotice> {

}
