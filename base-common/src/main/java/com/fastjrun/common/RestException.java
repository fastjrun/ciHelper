package com.fastjrun.common;

public class RestException extends RuntimeException {
	
	private static final long serialVersionUID = 286230051559125821L;
	public String errorCode;

	public RestException() {
	}

	public RestException(String errorCode) {

		this.errorCode = errorCode;

	}

	public RestException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
}