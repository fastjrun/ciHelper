package com.fastjrun.common;

public class TaskException extends RuntimeException {
	private static final long serialVersionUID = -282884768957526111L;
	public String errorCode;

	public TaskException() {
	}

	public TaskException(String errorCode) {

		this.errorCode = errorCode;

	}

	public TaskException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
}