package com.miniservice.constant;

public enum ResultEnum {

	/* 操作失败 */
	FAILURE(0, "failure"),

	/* 操作成功 */
	SUCCESS(200, "ok"),
	
	/* 参数错误 */
	INVALID_PARAMETER(1, "Parameter invalid"),
	
	/* 请求错误 */
	INVALID_REQUEST(2, "Request invalid"),
	
	/* 未登录 */
	INVALID_TOKEN(3, "please sign in"),
	
	/* 用户名或密码错误 */
	INVALID_USER(4, "wrong user name or password"),

	/* 手机号已存在 */
	PHONE_EXISTED(5, "Phone number is already registered."),

	/* 系统异常 */
	SYSTEM_ERROR(9999, "Exception");

	private int code;
	private String message;

	ResultEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
