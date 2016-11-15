package com.fastjrun.packet;

import java.io.Serializable;

public class BaseRestResponseHead implements Serializable {
    
    private static final long serialVersionUID = 6153642938936890248L;

    private String code;

    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(("BaseRestResponseHead"+" ["));
        sb.append("code");
        sb.append("=");
        sb.append(this.code);
        sb.append(",");
        sb.append("message");
        sb.append("=");
        sb.append(this.msg);
        sb.append("]");
        return sb.toString();
    }
}
