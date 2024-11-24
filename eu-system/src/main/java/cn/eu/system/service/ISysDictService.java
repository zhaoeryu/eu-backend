package cn.eu.system.service;

import cn.eu.common.base.service.IEuService;
import cn.eu.common.model.PageResult;
import cn.eu.system.domain.SysDict;
import cn.eu.system.model.dto.ImportResult;
import cn.eu.system.model.query.SysDictQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/5/31
 */
public interface ISysDictService extends IEuService<SysDict> {

    PageResult<SysDict> page(SysDictQueryCriteria criteria, Pageable pageable);

    List<SysDict> list(SysDictQueryCriteria criteria);

    void updateDict(SysDict entity);

    void exportTemplate(HttpServletResponse response) throws IOException;

    ImportResult importData(MultipartFile file, Integer importMode) throws IOException;

    void removeDictByIds(List<Integer> ids);
}
