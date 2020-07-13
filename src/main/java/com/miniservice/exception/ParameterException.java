package com.miniservice.exception;

import com.miniservice.constant.ResultEnum;

public class ParameterException extends ApplicationException {

	private static final long serialVersionUID = 659920926314624303L;

	public ParameterException(int code, String message) {
		super(code, message);
	}

	public ParameterException(String message) {
		super(ResultEnum.INVALID_PARAMETER.getCode(), message);
	}

	public ParameterException(ResultEnum result) {
		super(result.getCode(), result.getMessage());
	}

}
