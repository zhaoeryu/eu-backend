package cn.eu.system.service;

import cn.eu.common.base.service.IEuService;
import cn.eu.common.model.PageResult;
import cn.eu.system.domain.SysPost;
import cn.eu.system.model.dto.ImportResult;
import cn.eu.system.model.query.SysPostQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/5/31
 */
public interface ISysPostService extends IEuService<SysPost> {

    PageResult<SysPost> page(SysPostQueryCriteria criteria, Pageable pageable);

    List<SysPost> list(SysPostQueryCriteria criteria);

    List<Integer> getPostIdsByUserId(String userId);

    void exportTemplate(HttpServletResponse response) throws IOException;

    ImportResult importData(MultipartFile file, Integer importMode) throws IOException;
}
