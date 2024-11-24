package cn.eu.system.service;

import cn.eu.common.base.service.IEuService;
import cn.eu.common.model.PageResult;
import cn.eu.system.domain.SysDictDetail;
import cn.eu.system.model.dto.ImportResult;
import cn.eu.system.model.query.SysDictDetailQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/5/31
 */
public interface ISysDictDetailService extends IEuService<SysDictDetail> {

    PageResult<SysDictDetail> page(SysDictDetailQueryCriteria criteria, Pageable pageable);

    List<SysDictDetail> list(SysDictDetailQueryCriteria criteria);

    void exportTemplate(HttpServletResponse response) throws IOException;

    ImportResult importData(MultipartFile file, Integer importMode, Integer dictId) throws IOException;
}
