package com.fastjrun.orderbyhelper.sqlsource;

import com.fastjrun.mybatis.ConditionHelper;
import com.fastjrun.orderbyhelper.OrderByHelper;
import com.fastjrun.orderbyhelper.OrderByParser;

import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;

import java.util.List;

public class OrderByStaticSqlSource implements SqlSource, OrderBySqlSource {
    private String sql;
    private List<ParameterMapping> parameterMappings;
    private Configuration configuration;
    private SqlSource original;

    public OrderByStaticSqlSource(StaticSqlSource sqlSource) {
        MetaObject metaObject = SystemMetaObject.forObject(sqlSource);
        this.sql = (String) metaObject.getValue("sql");
        this.parameterMappings = (List<ParameterMapping>) metaObject.getValue("parameterMappings");
        this.configuration = (Configuration) metaObject.getValue("configuration");
        this.original = sqlSource;
    }

    public BoundSql getBoundSql(Object parameterObject) {
        
        String condition = ConditionHelper.getCondition();
        String tempSql = sql;
        if (condition != null && sql.indexOf("@{condition}")>=0) {
            tempSql = sql.replace("@{condition}", condition);
        }
        
        String orderBy = OrderByHelper.getOrderBy();
        if (orderBy != null) {
            tempSql = OrderByParser.converToOrderBySql(tempSql, orderBy);
        }
        return new BoundSql(configuration, tempSql, parameterMappings, parameterObject);
    }

    public SqlSource getOriginal() {
        return original;
    }

}
