package com.miniservice.dto.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class LoginReq implements Serializable {

	private static final long serialVersionUID = -7643005290444080350L;

	/**
	 * 用户名
	 */
	@ApiModelProperty(value = "phone", name = "phone", example = "phone")
	@JsonProperty(value = "phone")
	@NotBlank(message = "请输入用户名")
	private String userName;

	/**
	 * 密码
	 */
	@ApiModelProperty(value = "密码", name = "password", example = "password")
	@JsonProperty(value = "password")
	@NotBlank(message = "请输入密码")
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
