package cn.eu.common.utils.easyexcel;

import cn.eu.common.annotation.IEuEnum;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.lang.reflect.Field;
import java.util.Optional;

/**
 * easyexcel枚举类型转换
 * @author Eu.z
 * @since 2025/7/29
 */
public class EasyExcelEnumConverter implements Converter<IEuEnum<?>> {

    @Override
    public Class<?> supportJavaTypeKey() {
        return IEuEnum.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override

    public IEuEnum<?> convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty,
                                         GlobalConfiguration globalConfiguration) {
        Field field = contentProperty.getField();
        Class<IEuEnum<?>> type = (Class<IEuEnum<?>>) field.getType();
        return IEuEnum.of(type, cellData.getStringValue());
    }

    @Override
    public WriteCellData<String> convertToExcelData(IEuEnum<?> value, ExcelContentProperty contentProperty,
                                                    GlobalConfiguration globalConfiguration) {
        return new WriteCellData<>(Optional.ofNullable(value).map(IEuEnum::getDesc).orElse(""));
    }

}
