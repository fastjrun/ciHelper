
package com.fastjrun.mybatis.declare;


public class Condition {
    
    private StringBuffer sql; 
    
    public Condition()
    {
        this.sql = new StringBuffer();
    }
    
    
    public Condition and(String key, Object ... value)
    {
        sql.append(" and ");
        
        for(Object o: value)
        {
            if(o instanceof String)
            {
                o = "'"+o+"'";
            }
            key = key.replaceFirst("\\?", o+"");
        }
        
        
        
        
        sql.append(key);
        
        return this;
    }
    

    public Condition and(String key)
    {
        sql.append(" and " + key);
        return this;
    }
    
    @Override
    public String toString()
    {
        return this.sql.toString();
    }
    
}
