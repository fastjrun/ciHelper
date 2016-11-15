package com.fastjrun.packet;

import java.io.Serializable;

public class BaseCoreRequest<T extends BaseCoreRequestBody> implements Serializable {

    private static final long serialVersionUID = -2742361695624809103L;

    private BaseCoreRequestHead head;

    private T body;

    public BaseCoreRequest() {
    }

    public BaseCoreRequest(BaseCoreRequestHead head, T body) {
        this.head = head;
        this.body = body;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public BaseCoreRequestHead getHead() {
        return head;
    }

    public void setHead(BaseCoreRequestHead head) {
        this.head = head;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(("BaseCoreRequest"+" ["));
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
