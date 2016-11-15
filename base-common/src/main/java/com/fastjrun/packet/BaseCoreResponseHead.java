package com.fastjrun.packet;

import java.io.Serializable;

public class BaseCoreResponseHead implements Serializable {
    
    private static final long serialVersionUID = -7673251067875880180L;

    private String code;
    
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(("BaseCoreResponseHead"+" ["));
        sb.append("code");
        sb.append("=");
        sb.append(this.code);
        sb.append(",");
        sb.append("message");
        sb.append("=");
        sb.append(this.message);
        sb.append("]");
        return sb.toString();
    }

}
