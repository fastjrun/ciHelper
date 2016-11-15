
package com.fastjrun.mybatis.declare;

import java.util.Arrays;
import java.util.List;

public class SelectDeclare extends Declare {
    private List<String> columns;
    private String from;
    private Condition condition;
    private String orderBy;
    
    public SelectDeclare(String from)
    {
        this.from = from;
        this.condition = new Condition();
    }
    
    public SelectDeclare()
    {
        this.condition = new Condition();
    }
    
    
    public String getColumnsString()
    {
        StringBuffer sb = new StringBuffer();
        if(null == columns || columns.size()<=0)
        {
            return " * ";
        }
        for(int i = 0; i<columns.size(); i++)
        {
            if(i > 0)
            {
                sb.append(",");
            }
            sb.append(columns.get(i));
        }
        return sb.toString();
    }
    
   
    
    @Override
    public void createSql()
    {
        
        this.sql = "select "+ getColumnsString()+ " from " + from;
        
        if(null != condition && !condition.equals(""))
        {
            sql = sql + " where 1 = 1 " + condition ;
        }
        
        if(null != orderBy && !orderBy.equals(""))
        {
            sql = sql + " order by " + orderBy;
        }
    }
    
    
    
    public SelectDeclare columns(String ... columns)
    {
        this.columns = Arrays.asList(columns);
        return this;
    }
    
    public SelectDeclare from(String from)
    {
        this.from = from;
        return this;
    }
    
    public SelectDeclare orderBy(String orderBy)
    {
        this.orderBy = orderBy;
        return this;
    }
    
    public SelectDeclare condition(Condition condition)
    {
        this.condition = condition;
        return this;
    }
    
    public SelectDeclare and(String key, Object ... value)
    {
        this.condition.and(key, value);
        return this;
    }
}
