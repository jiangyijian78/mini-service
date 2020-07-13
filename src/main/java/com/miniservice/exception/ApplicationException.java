package com.miniservice.exception;

import com.miniservice.constant.ResultEnum;

public class ApplicationException extends Exception {

	private static final long serialVersionUID = 7066306575200747704L;

	protected int code;
	protected String message;

	public ApplicationException() {
		super();
	}

	public ApplicationException(String message) {
		super(message);
		this.message = message;
	}

	public ApplicationException(int code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}

	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}

	public ApplicationException(ResultEnum result) {
		super(result.getMessage());
		this.code = result.getCode();
		this.message = result.getMessage();
	}
}
