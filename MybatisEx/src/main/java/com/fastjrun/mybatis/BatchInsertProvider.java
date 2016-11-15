package com.fastjrun.mybatis;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SqlBuilder;

public class BatchInsertProvider {
    public String sql(Map<String, Object> para) {
        String tableName = (String) para.get("tableName");
        Class<?> className = (Class<?>) para.get("className");
        List<Object> beans = (List<Object>) para.get("beans");

        StringBuffer sql = new StringBuffer();

        sql.append("insert into ");
        sql.append(tableName);
        sql.append("(");

        Field[] fields = className.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            if (!"id".equals(fields[i].getName())
                    && !"serialVersionUID".equals(fields[i].getName())) {
                sql.append(fields[i].getName());
                if (i != fields.length - 1) {
                    sql.append(",");
                }
            }
        }
        sql = new StringBuffer(sql.substring(0, sql.length() - 1));

        sql.append(") ");
        sql.append("values");

        for (Object o : beans) {

            Class<? extends Object> beanClass = o.getClass();

            if (beanClass.getName().equals(className.getName())) {
                sql.append("(");
                for (int i = 0; i < fields.length; i++) {
                    if (!"id".equals(fields[i].getName())
                            && !"serialVersionUID".equals(fields[i].getName())) {
                        String value = "";

                        Field beanField = null;
                        try {
                            beanField = beanClass.getDeclaredField(fields[i]
                                    .getName());
                        } catch (NoSuchFieldException e1) {
                            e1.printStackTrace();
                        } catch (SecurityException e1) {
                            e1.printStackTrace();
                        }

                        beanField.setAccessible(true);

                        Object beanFieldValue = null;
                        try {
                            beanFieldValue = beanField.get(o);
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        Class<?> beanFieldType = beanField.getType();

                        if (null != beanFieldValue) {
                            if (beanFieldValue instanceof String) {
                                value = "'" + beanFieldValue + "'";
                            } else {
                                value = beanFieldValue.toString();
                            }
                        } else {
                            if (beanFieldType.getName().equals(
                                    String.class.getName())) {
                                value = "''";
                            } else {
                                value = "null";
                            }
                        }

                        sql.append(value);
                        sql.append(",");
                    }
                }
                sql = new StringBuffer(sql.substring(0, sql.length() - 1));
                sql.append("),");
            } else {
                throw new RuntimeException("批量插入失败，className与beans的类型不匹配");
            }

        }

        return sql.substring(0, sql.length() - 1);
    }

}
