package com.miniservice.dto.response;

import java.io.Serializable;

public class SigninResp implements Serializable {

	private static final long serialVersionUID = 7351088787904741589L;

	/**
	 * 签到结果
	 */
	private Boolean signinFlag;

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getSigninFlag() {
		return signinFlag;
	}

	public void setSigninFlag(Boolean signinFlag) {
		this.signinFlag = signinFlag;
	}
}
