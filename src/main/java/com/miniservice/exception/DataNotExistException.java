package com.miniservice.exception;

import com.miniservice.constant.ResultEnum;

public class DataNotExistException extends ApplicationException {

	private static final long serialVersionUID = 659920926314624303L;

	public DataNotExistException(int code, String message) {
		super(code, message);
	}

	public DataNotExistException(ResultEnum result) {
		super(result.getCode(), result.getMessage());
	}

}
