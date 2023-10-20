package cn.eu.system.easyexcel.converter;

import cn.eu.common.utils.SpringContextHolder;
import cn.eu.system.domain.SysDept;
import cn.eu.system.service.ISysDeptService;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Objects;

/**
 * @author zhaoeryu
 * @since 2023/8/25
 */
@Slf4j
public class SysDeptConverter implements Converter<Integer> {

    private List<SysDept> deptList;

    @Override
    public Class<?> supportJavaTypeKey() {
        return Integer.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Integer convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        String stringValue = cellData.getStringValue();

        loadDeptList();

        return deptList.stream()
                .filter(item -> StrUtil.equals(item.getDeptName(), stringValue))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("[%s]部门不存在", stringValue)))
                .getId();
    }

    @Override
    public WriteCellData<String> convertToExcelData(Integer value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {

        loadDeptList();

        String deptName = deptList.stream()
                .filter(item -> Objects.equals(item.getId(), value))
                .findFirst()
                .orElse(new SysDept())
                .getDeptName();

        return new WriteCellData<>(deptName);
    }

    private void loadDeptList() {
        if (deptList == null || deptList.size() == 0) {
            log.debug("===========================");
            log.debug("加载部门列表");
            log.debug("===========================");
            deptList = SpringContextHolder.getBean(ISysDeptService.class).listWithConcatenatedDeptName();
        }
    }

}
