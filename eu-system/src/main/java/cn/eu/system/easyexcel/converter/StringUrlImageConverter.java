package cn.eu.system.easyexcel.converter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.io.IOException;

public class StringUrlImageConverter implements Converter<String> {
    @Override
    public Class<?> supportJavaTypeKey() {
        return String.class;
    }

    @Override
    public WriteCellData<?> convertToExcelData(String value, ExcelContentProperty contentProperty,
                                               GlobalConfiguration globalConfiguration) throws IOException {
        if (StrUtil.isBlank(value)) {
            return new WriteCellData<>();
        }
        // 根据url获取图片字节数组
        byte[] bytes = HttpUtil.downloadBytes(value);
        return new WriteCellData<>(bytes);
    }

}