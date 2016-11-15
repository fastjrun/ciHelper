package com.fastjrun.packet;

import java.io.Serializable;

public class BaseRestResponse<T extends BaseRestResponseBody> implements Serializable {
    
    private static final long serialVersionUID = -1612697269017956017L;

    private BaseRestResponseHead head;

    private T body;

    public BaseRestResponse() {

    }

    public BaseRestResponse(BaseRestResponseHead head, T body) {
        super();
        this.head = head;
        this.body = body;
    }

    public BaseRestResponseHead getHead() {
        return head;
    }

    public void setHead(BaseRestResponseHead head) {
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
        sb.append(("BaseRestResponse" + " ["));
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
