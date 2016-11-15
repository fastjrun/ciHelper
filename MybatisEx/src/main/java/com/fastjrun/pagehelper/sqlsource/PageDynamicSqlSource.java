package com.fastjrun.pagehelper.sqlsource;

import java.util.Map;

import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.xmltags.DynamicContext;
import org.apache.ibatis.scripting.xmltags.DynamicSqlSource;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.session.Configuration;

import com.fastjrun.orderbyhelper.sqlsource.OrderBySqlSource;
import com.fastjrun.orderbyhelper.sqlsource.OrderByStaticSqlSource;
import com.fastjrun.pagehelper.Constant;
import com.fastjrun.pagehelper.parser.Parser;

public class PageDynamicSqlSource extends PageSqlSource implements OrderBySqlSource, Constant {
    private Configuration configuration;
    private SqlNode rootSqlNode;
    private SqlSource original;
    private Parser parser;

    public PageDynamicSqlSource(DynamicSqlSource sqlSource, Parser parser) {
        MetaObject metaObject = SystemMetaObject.forObject(sqlSource);
        this.configuration = (Configuration) metaObject.getValue("configuration");
        this.rootSqlNode = (SqlNode) metaObject.getValue("rootSqlNode");
        this.original = sqlSource;
        this.parser = parser;
    }

    @Override
    protected BoundSql getDefaultBoundSql(Object parameterObject) {
        DynamicContext context = new DynamicContext(configuration, parameterObject);
        rootSqlNode.apply(context);
        SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(configuration);
        Class<?> parameterType = parameterObject == null ? Object.class : parameterObject.getClass();
        SqlSource sqlSource = sqlSourceParser.parse(context.getSql(), parameterType, context.getBindings());
        sqlSource = new OrderByStaticSqlSource((StaticSqlSource) sqlSource);
        BoundSql boundSql = sqlSource.getBoundSql(parameterObject);
        //设置条件参数
        for (Map.Entry<String, Object> entry : context.getBindings().entrySet()) {
            boundSql.setAdditionalParameter(entry.getKey(), entry.getValue());
        }
        return boundSql;
    }

    @Override
    protected BoundSql getCountBoundSql(Object parameterObject) {
        DynamicContext context = new DynamicContext(configuration, parameterObject);
        rootSqlNode.apply(context);
        SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(configuration);
        Class<?> parameterType = parameterObject == null ? Object.class : parameterObject.getClass();
        SqlSource sqlSource = sqlSourceParser.parse(context.getSql(), parameterType, context.getBindings());
        BoundSql boundSql = sqlSource.getBoundSql(parameterObject);
        sqlSource = new StaticSqlSource(configuration, parser.getCountSql(boundSql.getSql()), boundSql.getParameterMappings());
        boundSql = sqlSource.getBoundSql(parameterObject);
        //设置条件参数
        for (Map.Entry<String, Object> entry : context.getBindings().entrySet()) {
            boundSql.setAdditionalParameter(entry.getKey(), entry.getValue());
        }
        return boundSql;
    }

    @Override
    protected BoundSql getPageBoundSql(Object parameterObject) {
        DynamicContext context;
        //由于增加分页参数后会修改parameterObject的值，因此在前面处理时备份该值
        //如果发现参数是Map并且包含该KEY，就使用备份的该值
        //解决bug#25:http://git.oschina.net/free/Mybatis_PageHelper/issues/25
        if (parameterObject != null
                && parameterObject instanceof Map
                && ((Map) parameterObject).containsKey(ORIGINAL_PARAMETER_OBJECT)) {
            context = new DynamicContext(configuration, ((Map) parameterObject).get(ORIGINAL_PARAMETER_OBJECT));
        } else {
            context = new DynamicContext(configuration, parameterObject);
        }
        rootSqlNode.apply(context);
        SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(configuration);
        Class<?> parameterType = parameterObject == null ? Object.class : parameterObject.getClass();
        SqlSource sqlSource = sqlSourceParser.parse(context.getSql(), parameterType, context.getBindings());
        sqlSource = new OrderByStaticSqlSource((StaticSqlSource) sqlSource);
        BoundSql boundSql = sqlSource.getBoundSql(parameterObject);
        sqlSource = new StaticSqlSource(configuration, parser.getPageSql(boundSql.getSql()), parser.getPageParameterMapping(configuration, boundSql));
        boundSql = sqlSource.getBoundSql(parameterObject);
        //设置条件参数
        for (Map.Entry<String, Object> entry : context.getBindings().entrySet()) {
            boundSql.setAdditionalParameter(entry.getKey(), entry.getValue());
        }
        return boundSql;
    }

    public SqlSource getOriginal() {
        return original;
    }

}