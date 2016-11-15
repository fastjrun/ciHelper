package com.fastjrun.packet;

import java.io.Serializable;

public class BaseRestRequest<T extends BaseRestRequestBody> implements Serializable {

    private static final long serialVersionUID = 7834907964983017562L;

    private BaseRestRequestHead head;

    private T body;

    public BaseRestRequest() {

    }

    public BaseRestRequest(BaseRestRequestHead head, T body) {
        super();
        this.head = head;
        this.body = body;
    }

    public BaseRestRequestHead getHead() {
        return head;
    }

    public void setHead(BaseRestRequestHead head) {
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
        sb.append(("BaseRestRequest"+" ["));
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
