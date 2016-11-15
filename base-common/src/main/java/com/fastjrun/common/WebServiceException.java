package com.fastjrun.common;

public class WebServiceException extends RuntimeException {
    private static final long serialVersionUID = -8766445727501813995L;

    private String code;

    private String msg;

    public WebServiceException(String code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

}
