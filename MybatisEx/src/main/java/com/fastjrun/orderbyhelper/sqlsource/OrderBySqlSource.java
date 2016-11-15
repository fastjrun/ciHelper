package com.fastjrun.orderbyhelper.sqlsource;

import org.apache.ibatis.mapping.SqlSource;

public interface OrderBySqlSource {

    /**
     * 获取原来的sqlSource
     *
     * @return
     */
    SqlSource getOriginal();

}
