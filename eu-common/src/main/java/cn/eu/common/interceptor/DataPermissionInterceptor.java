package cn.eu.common.interceptor;

import cn.eu.common.utils.DataScopeContextHelper;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * 数据权限拦截器
 * <p>
 * 该拦截器在Mybatis中的顺序很重要，需要在PageInterceptor之前执行（也就是该拦截器要配置在Mybatis拦截器链的PageInterceptor拦截器之后），
 * 因为PageInterceptor会另外生成一个COUNT查询，
 * 如果数据权限拦截器在PageInterceptor之后执行，那么还需要对PageInterceptor生成的COUNT进行拼接数据权限的查询条件
 * </p>
 * @see <a href="https://pagehelper.github.io/docs/interceptor/">QueryInterceptor规范</a>
 * @author zhaoeryu
 * @since 2023/8/7
 */
@Slf4j
@Intercepts({
    @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
    @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
})
public class DataPermissionInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.debug("DataPermissionInterceptor 开始执行");
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        Object parameter = args[1];
        RowBounds rowBounds = (RowBounds) args[2];
        ResultHandler<?> resultHandler = (ResultHandler<?>) args[3];
        Executor executor = (Executor) invocation.getTarget();
        CacheKey cacheKey;
        BoundSql boundSql;
        //由于逻辑关系，只会进入一次
        if(args.length == 4){
            //4 个参数时
            boundSql = ms.getBoundSql(parameter);
            cacheKey = executor.createCacheKey(ms, parameter, rowBounds, boundSql);
        } else {
            //6 个参数时
            cacheKey = (CacheKey) args[4];
            boundSql = (BoundSql) args[5];
        }

        // SQL处理
        boundSql = this.doSqlHandle(boundSql, ms, parameter);

        List<Object> query = executor.query(ms, parameter, rowBounds, resultHandler, cacheKey, boundSql);
        log.debug("DataPermissionInterceptor 执行结束");
        return query;
    }

    private BoundSql doSqlHandle(BoundSql boundSql, MappedStatement ms, Object parameter) throws JSQLParserException {
        // 拼接数据权限过滤的SQL
        String dataScopeSql = DataScopeContextHelper.get();
        if (StrUtil.isBlank(dataScopeSql)) {
            // 如果dataScopeSql为空，则不需要拼接数据权限的SQL
            return boundSql;
        }
        // 解析为JSql对象，然后拼接数据权限的SQL到where条件中，拼接完重新生成BoundSql对象
        Select select = (Select) CCJSqlParserUtil.parse(boundSql.getSql());
        PlainSelect plainSelect = select.getPlainSelect();
        // 判断是否有where条件，如果没有直接拼接，如果有则拼接and
        if (plainSelect.getWhere() == null) {
            plainSelect.setWhere(CCJSqlParserUtil.parseCondExpression(dataScopeSql));
        } else {
            AndExpression andExpression = new AndExpression();
            andExpression.setLeftExpression(plainSelect.getWhere());
            andExpression.setRightExpression(CCJSqlParserUtil.parseCondExpression(dataScopeSql));
            plainSelect.setWhere(andExpression);
        }
        String newSql = plainSelect.toString();

        boundSql = new BoundSql(ms.getConfiguration(), newSql, boundSql.getParameterMappings(), parameter);
        if (log.isDebugEnabled()) {
            System.err.println(StrUtil.format("[{}]\n{}", ms.getId(), dataScopeSql));
            System.err.println("---------------");
            System.err.println(StrUtil.format("[{}]\n{}", ms.getId(), newSql));
        }
        return boundSql;
    }

}
