
package com.fastjrun.mybatis.declare;

public class UpdateDeclare extends Declare {
    
    private String table;
    
    private Settings settings;
    
    private Condition condition;
    
    public UpdateDeclare()
    {
        this.settings = new Settings();
        this.condition = new Condition();
    }
    
    public UpdateDeclare(String table)
    {
        this.table = table;
        this.settings = new Settings();
        this.condition = new Condition();
    }
    
    public UpdateDeclare addSet(String key, Object value)
    {
        this.settings.add(key, value);
        return this;
    }
       
    public UpdateDeclare and(String key, Object ... value)
    {
        this.condition.and(key, value);
        return this;
    }
    
    public UpdateDeclare table(String table)
    {
        this.table = table;
        return this;
    }
    
    @Override
    public void createSql()
    {
        
        this.sql = "update "+ table+ " set " + settings.toString();
        
        if(null != condition && !condition.equals(""))
        {
            sql = sql + " where 1 = 1 " + condition ;
        }
    }
    
}
