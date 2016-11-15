package com.fastjrun.packet.res;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class RestResult {
    
    Map<String, Object> head;
    
    Map<String, Object> body;
    
    
    public Map<String, Object> getHead() {
        return head;
    }

    public void setHead(Map<String, Object> head) {
        this.head = head;
    }

    public Map<String, Object> getBody() {
        return body;
    }

    public void setBody(Map<String, Object> body) {
        this.body = body;
    }

    public RestResult()
    {
        this.head = new HashMap<String, Object>();
        this.body = new HashMap<String, Object>();
    }
    /**
     * 
    * @Title: hput
    * @Description: 头里添加值
    * @param @param key
    * @param @param value
    * @param @return
    * @return RestResult
    * @throws
     */
    public RestResult hput(String key, Object value)
    {
        this.head.put(key, value);
        return this;
    }
    /**
     * 
    * @Title: bput
    * @Description: body中添加值
    * @param @param key
    * @param @param value
    * @param @return
    * @return RestResult
    * @throws
     */
    public RestResult bput(String key, Object value)
    {
        this.body.put(key, value);
        return this;
    }
    
    /**
     * 
    * @Title: bput
    * @Description: javabean的属性放入body
    * @param @param value
    * @param @return
    * @return RestResult
    * @throws
     */
    public RestResult bput(Object value)
    {
        if(value instanceof Map)
        {
            Map<Object,Object> mapValue = (Map<Object,Object>)value;
            for(Object key: mapValue.keySet())
            {
                this.bput(key.toString(), mapValue.get(key));
            }
            return this;
        }
        Field[] fields = value.getClass().getDeclaredFields();
        
        for(Field f: fields)
        {
            f.setAccessible(true);
            String name = f.getName();
            Object object = null;
            try {
                object = f.get(value);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            this.body.put(name, object);
        }
        
        
        return this;
    }
    
    
}