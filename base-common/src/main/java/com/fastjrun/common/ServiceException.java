package com.fastjrun.common;

public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -5732470572218412996L;

    private String code;

    private String msg;

    public ServiceException(String code, String msg) {
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
