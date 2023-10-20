package cn.eu.generate.test;

import cn.eu.generate.model.dto.GenerateTemplateDto;
import cn.eu.generate.utils.VelocityHelper;
import cn.hutool.core.util.StrUtil;
import org.apache.velocity.VelocityContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author zhaoeryu
 * @since 2023/6/27
 */
public class VelocityTest {

    public static void main1(String[] args) {
        String tableName = "sys_post";

        System.out.println(StrUtil.toCamelCase(tableName));
        // 首字母大写
        System.out.println(StrUtil.upperFirst(StrUtil.toCamelCase(tableName)));
    }
    public static void main(String[] args) {
        VelocityHelper.init();

        List<Map<String, Object>> columns = new LinkedList<>();
        Map<String, Object> column = new HashMap<>();
        column.put("comment", "岗位名称");
        column.put("columnKey", "PRI");
        column.put("javaType", "String");
        column.put("javaField", "id");
        column.put("isExcelExport", false);
        column.put("isNotNull", false);
        column.put("query", false);
        column.put("formType", "input");
        column.put("table", false);
        column.put("form", false);
        column.put("required", false);
        columns.add(column);
        column = new HashMap<>();
        column.put("comment", "岗位名称");
        column.put("columnKey", null);
        column.put("javaType", "String");
        column.put("javaField", "postName");
        column.put("isExcelExport", true);
        column.put("isNotNull", true);
        column.put("query", true);
        column.put("formType", "input");
        column.put("table", true);
        column.put("form", true);
        column.put("required", true);
        columns.add(column);

        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("package", "cn.eu.system");
        velocityContext.put("module", "system");
        velocityContext.put("author", "zhaoeryu");
        velocityContext.put("date", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        velocityContext.put("ClassName", "SysPost");
        velocityContext.put("className", "sysPost");
        velocityContext.put("tableComment", "岗位");
        velocityContext.put("permissionPrefix", "system:post");
        velocityContext.put("tableName", "sys_post");
        velocityContext.put("pkColumnType", "Integer");
        velocityContext.put("auto", false);
        velocityContext.put("delShowField", "postName");
        velocityContext.put("columns", columns);

        List<GenerateTemplateDto> templates = VelocityHelper.render(velocityContext);

        templates.forEach(item -> {
            System.out.println("===========================================================");
            System.out.println(item.getPath());
            System.out.println("--------------------");
            System.out.println(item.getCode());
        });
    }

}
