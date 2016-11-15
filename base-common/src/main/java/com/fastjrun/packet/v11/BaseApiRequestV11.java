package com.fastjrun.packet.v11;

import java.io.Serializable;

public class BaseApiRequestV11<T extends BaseApiRequestBodyV11> implements Serializable {
    
    private static final long serialVersionUID = 6799407636932332361L;

    private BaseApiRequestHeadV11 head;

    private T body;

    public BaseApiRequestV11() {

    }

    public BaseApiRequestV11(BaseApiRequestHeadV11 head, T body) {
        super();
        this.head = head;
        this.body = body;
    }

    public BaseApiRequestHeadV11 getHead() {
        return head;
    }

    public void setHead(BaseApiRequestHeadV11 head) {
        this.head = head;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(("BaseApiRequestV11"+" ["));
        sb.append("head");
        sb.append("=");
        sb.append(this.head);
        sb.append(",");
        sb.append("body");
        sb.append("=");
        sb.append(this.body);
        sb.append("]");
        return sb.toString();
    }
}
