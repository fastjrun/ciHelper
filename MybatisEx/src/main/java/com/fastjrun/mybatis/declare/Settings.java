package com.fastjrun.mybatis.declare;

import java.util.HashMap;
import java.util.Map;

public class Settings {
    private Map<String, Object> settings;

    private StringBuffer sql;

    public Settings() {
        this.settings = new HashMap<String, Object>();
        this.sql = new StringBuffer();
    }

    public Settings add(String key, Object value) {
        if (settings.size() > 0) {
            sql.append(", ");
        }
        settings.put(key, value);
        sql.append(key);
        sql.append(" = ");

        if (value instanceof String) {
            value = "'" + value + "'";
        }
        sql.append(value);
        return this;
    }

    @Override
    public String toString() {
        return this.sql.toString();
    }
}
