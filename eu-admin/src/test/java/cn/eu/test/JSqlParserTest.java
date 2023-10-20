package cn.eu.test;

import cn.hutool.core.collection.CollUtil;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.util.SelectUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/8/7
 */
public class JSqlParserTest {

    /**
     * 单表SQL查询
     */
    @Test
    public void testSelectOneTable() throws JSQLParserException {
        // 单表全量
        Table table = new Table("sys_user");
        Select select = SelectUtils.buildSelectFromTable(table);
        System.out.println(select);
        
        // 指定列查询
        Select buildSelectFromTableAndExpressions = SelectUtils.buildSelectFromTableAndExpressions(table, "id", "username");
        System.out.println(buildSelectFromTableAndExpressions);

        // WHERE 条件
        System.out.println(buildWhere(select, new EqualsTo())); // =
        System.out.println(buildWhere(select, new NotEqualsTo())); // != <>
        System.out.println(buildWhere(select, new GreaterThan())); // >
        System.out.println(buildWhere(select, new GreaterThanEquals())); // >=
        System.out.println(buildWhere(select, new MinorThan())); // <
        System.out.println(buildWhere(select, new MinorThanEquals())); // <=
        System.out.println(buildWhere(select, new IsNullExpression())); // is null, is not null

        // WHERE LIKE
        LikeExpression likeExpression = new LikeExpression();
        likeExpression.setNot(true);
        likeExpression.setLeftExpression(new Column("username"));
        likeExpression.setRightExpression(new StringValue("%admin%"));
        System.out.println(buildWhereString(select, likeExpression)); // SELECT * FROM sys_user WHERE username LIKE '%admin%'

        // WHERE IN
        InExpression inExpression = new InExpression();
        inExpression.setNot(true);
        inExpression.setLeftExpression(new Column("id"));
        ItemsList itemsList = new ExpressionList(new StringValue("1"), new StringValue("2"));
        inExpression.setRightItemsList(itemsList);
        System.out.println(buildWhereString(select, inExpression)); // SELECT * FROM sys_user WHERE id NOT IN ('1', '2')

        // WHERE BETWEEN AND
        Between between = new Between();
        between.setNot(true);
        between.setLeftExpression(new Column("id"));
        between.setBetweenExpressionStart(new StringValue("1"));
        between.setBetweenExpressionEnd(new StringValue("2"));
        System.out.println(buildWhereString(select, between)); // SELECT * FROM sys_user WHERE id NOT BETWEEN '1' AND '2'

        //  WHERE AND 多个条件结合,都需要成立
        AndExpression andExpression = new AndExpression();
        andExpression.setLeftExpression(likeExpression);
        andExpression.setRightExpression(inExpression);
        System.out.println(buildWhereString(select, andExpression)); // SELECT * FROM sys_user WHERE username LIKE '%admin%' AND id NOT IN ('1', '2')

        //  WHERE OR 多个条件满足一个条件成立返回
        OrExpression orExpression = new OrExpression();
        orExpression.setLeftExpression(likeExpression);
        orExpression.setRightExpression(inExpression);
        System.out.println(buildWhereString(select, orExpression)); // SELECT * FROM sys_user WHERE username LIKE '%admin%' OR id NOT IN ('1', '2')

        // ORDER BY 排序
        OrderByElement orderByElement = new OrderByElement();
        orderByElement.setAsc(false);
        orderByElement.setExpression(new Column("id"));
        OrderByElement orderByElement2 = new OrderByElement();
        orderByElement2.setAsc(true);
        orderByElement2.setExpression(new Column("username"));
        PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
        plainSelect.setOrderByElements(CollUtil.newArrayList(orderByElement, orderByElement2));
        System.out.println(plainSelect); // SELECT * FROM sys_user ORDER BY id DESC

        // 拼接自定义sql
        andExpression = new AndExpression();
        andExpression.setLeftExpression(likeExpression);
        andExpression.setRightExpression(CCJSqlParserUtil.parseCondExpression("dept_id = 2"));
        plainSelect.setWhere(andExpression);
        System.out.println(plainSelect); // SELECT * FROM sys_user WHERE username LIKE '%admin%' AND 1=1
    }

    private String buildWhere(Select select, BinaryExpression expression) {
        expression.setLeftExpression(new Column("id"));
        expression.setRightExpression(new StringValue("1"));
        return buildWhereString(select, expression);
    }
    private String buildWhere(Select select, IsNullExpression expression) {
        expression.setNot(true);
        expression.setLeftExpression(new Column("id"));
        return buildWhereString(select, expression);
    }
    private String buildWhereString(Select select, Expression expression) {
        PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
        plainSelect.setWhere(expression);
        return plainSelect.toString();
    }


    /**
     * 多表SQL查询
     * JOIN / INNER JOIN: 如果表中有至少一个匹配，则返回行
     * LEFT JOIN: 即使右表中没有匹配，也从左表返回所有的行
     * RIGHT JOIN: 即使左表中没有匹配，也从右表返回所有的行
     * FULL JOIN: 只要其中一个表中存在匹配，就返回行
     */
    @Test
    public void testSelectManyTable(){
        Table userTable = new Table("sys_user").withAlias(new Alias("u").withUseAs(true));
        Table deptTable = new Table("sys_dept").withAlias(new Alias("d", false));
        PlainSelect plainSelect = new PlainSelect().addSelectItems(new SelectExpressionItem(new Column("u.*"))).withFromItem(userTable);
        System.out.println(plainSelect); // SELECT u.* FROM sys_user AS u

        // JOIN ON 如果表中有至少一个匹配，则返回行
        Join join = new Join();
//         join.setLeft(true); // LEFT JOIN
//         join.setRight(true);  // RIGHT JOIN
//         join.setFull(true); // FULL JOIN
         join.setInner(true); // INNER JOIN
        join.withRightItem(deptTable);
        EqualsTo equalsTo = new EqualsTo();
        equalsTo.setLeftExpression(new Column("u.dept_id"));
        equalsTo.setRightExpression(new Column("d.id"));
        join.setOnExpression(equalsTo); // JOIN sys_dept d ON u.dept_id = d.id
        plainSelect.addJoins(join);
        System.out.println(plainSelect); // SELECT u.* FROM sys_user AS u INNER JOIN sys_dept d ON u.dept_id = d.id
    }

    /**
     * SQL 函数
     * SELECT function(列) FROM 表
     */
    @Test
    public void testFun() throws JSQLParserException {
        Table table = new Table("sys_user").withAlias(new Alias("u"));
        PlainSelect plainSelect = new PlainSelect().withFromItem(table);
        List<SelectItem> selectItemList = new ArrayList<>();
        SelectExpressionItem selectExpressionItem = new SelectExpressionItem();
        selectExpressionItem.setExpression(new Column(table, "id"));
        selectItemList.add(selectExpressionItem);
        selectExpressionItem = new SelectExpressionItem();
        selectExpressionItem.setExpression(new Column(table, "username"));
        selectItemList.add(selectExpressionItem);

        // COUNT
        selectExpressionItem = new SelectExpressionItem();
        selectExpressionItem.setAlias(new Alias("count")); // 设置别名
        Function function = new Function();
        function.setName("COUNT"); // 设置函数名
        ExpressionList expressionList = new ExpressionList(); // 创建表达式列表
        expressionList.setExpressions(CollUtil.newArrayList(new Column(table, "id"))); // 设置表达式列表参数
        function.setParameters(expressionList); // 设置函数参数
        selectExpressionItem.setExpression(function); // 设置函数表达式
        selectItemList.add(selectExpressionItem);

        plainSelect.setSelectItems(selectItemList);
        System.out.println(plainSelect); // SELECT u.id, u.username, COUNT(u.id) AS count FROM sys_user AS u


    }

}
