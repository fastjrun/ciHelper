package com.fastjrun.packet;

import com.fastjrun.common.AbstractEntity;

public class BaseRestBeanResponseBody extends BaseRestResponseBody {

    private AbstractEntity abstractEntity;

    public AbstractEntity getAbstractEntity() {
        return abstractEntity;
    }

    public void setAbstractEntity(AbstractEntity abstractEntity) {
        this.abstractEntity = abstractEntity;
    }
}
