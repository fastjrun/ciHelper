package com.fastjrun.mybatis;

import java.util.List;
import java.util.Properties;

import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.RawSqlSource;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

@Intercepts(@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
        RowBounds.class, ResultHandler.class }))
public class ConditionHelper implements Interceptor {
    private static final ThreadLocal<String> CONDITION = new ThreadLocal<String>();

    protected static final Log log = LogFactory.getLog("mybatis Dynamic Search");

    public static String getCondition() {
        try {

            String condition = CONDITION.get();
            if (condition == null || condition.length() == 0) {
                return "";
            }
            return condition;
        } finally {
            clear();
        }
    }

    /**
     * 增加condition
     *
     * @param orderBy
     */
    public static void condition(String condition) {
        CONDITION.set(condition);
    }

    /**
     * 清除本地变量
     */
    public static void clear() {
        CONDITION.remove();
    }

    /**
     * 是否是condition
     *
     * @param ms
     * @return
     */
    public static boolean hasCondition() {
        String condition = CONDITION.get();
        if (condition == null || condition.length() == 0) {
            return false;
        }
        return true;
    }

    /**
     * 不支持动态sql和provider
     *
     * @param invocation
     * @throws Throwable
     */
    public static void processIntercept(Invocation invocation) throws Throwable {

        final Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        MetaObject msObject = SystemMetaObject.forObject(ms);
        SqlSource sqlSource = ms.getSqlSource();
        if (hasCondition()) {
            // 判断是否自带order by，自带的情况下作为默认排序
            if (sqlSource instanceof StaticSqlSource) {
                StaticSqlSource oldSqlSource = (StaticSqlSource) sqlSource;
                MetaObject metaObject = SystemMetaObject.forObject(sqlSource);

                String sql = (String) metaObject.getValue("sql");

                String condition = getCondition();
                String tempSql = sql;
                if (condition != null && sql.indexOf("@{condition}") >= 0) {
                    tempSql = sql.replace("@{condition}", condition);
                }

                List<ParameterMapping> parameterMappings = (List<ParameterMapping>) metaObject
                        .getValue("parameterMappings");
                Configuration configuration = (Configuration) metaObject.getValue("configuration");

                msObject.setValue("sqlSource", new StaticSqlSource(configuration, tempSql, parameterMappings));

            } else if (sqlSource instanceof RawSqlSource) {

                RawSqlSource oldSqlSource = (RawSqlSource) sqlSource;

                MetaObject metaObject = SystemMetaObject.forObject(sqlSource);

                StaticSqlSource staticSqlSource = (StaticSqlSource) metaObject.getValue("sqlSource");

                MetaObject staticMetaObject = SystemMetaObject.forObject(staticSqlSource);

                String sql = (String) staticMetaObject.getValue("sql");

                String condition = getCondition();
                String tempSql = sql;
                if (condition != null && sql.indexOf("@{condition}") >= 0) {
                    tempSql = sql.replace("@{condition}", condition);
                }

                List<ParameterMapping> parameterMappings = (List<ParameterMapping>) staticMetaObject
                        .getValue("parameterMappings");
                Configuration configuration = (Configuration) staticMetaObject.getValue("configuration");

                msObject.setValue("sqlSource", new StaticSqlSource(configuration, tempSql, parameterMappings));
            } else {
                invocation.proceed();
                // throw new RuntimeException("无法处理该类型[" + sqlSource.getClass()
                // + "]的SqlSource");
            }
        }
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        try {
            processIntercept(invocation);
            return invocation.proceed();
        } finally {
            clear();
        }
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
