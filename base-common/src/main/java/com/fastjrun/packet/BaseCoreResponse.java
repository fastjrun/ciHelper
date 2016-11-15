package com.fastjrun.packet;

import java.io.Serializable;

public class BaseCoreResponse<T extends BaseCoreResponseBody> implements Serializable {
    
    private static final long serialVersionUID = -7722964337906337481L;

    private BaseCoreResponseHead head;

    private T body;

    public BaseCoreResponse() {
    }

    public BaseCoreResponse(BaseCoreResponseHead head, T body) {
        this.head = head;
        this.body = body;
    }

    public BaseCoreResponseHead getHead() {
        return head;
    }

    public void setHead(BaseCoreResponseHead head) {
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
        sb.append(("BaseCoreResponse" + " ["));
        sb.append("field [");
        sb.append("head");
        sb.append("=");
        sb.append(this.head);
        sb.append(",");
        sb.append("body");
        sb.append("=");
        sb.append(this.body);
        sb.append("]");
        sb.append("]");
        return sb.toString();
    }

}
