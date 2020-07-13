package com.miniservice.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class RegisteredReq implements Serializable {

	private static final long serialVersionUID = -7329807772552328282L;

	@ApiModelProperty(value = "user_name", name = "user_name", example = "user_name")
	@JsonProperty(value = "user_name")
	@NotBlank(message = "user name can not be blank")
	private String userName;

	@ApiModelProperty(value = "password", name = "password", example = "password")
	@JsonProperty(value = "password")
	@NotBlank(message = "password can not be blank")
	private String password;

	@ApiModelProperty(value = "phone", name = "phone", example = "phone")
	@JsonProperty(value = "phone")
	@Pattern(regexp = "^1[3456789]\\d{9}$", message = "Invalid phone number.")
	private String phone;

	@ApiModelProperty(value = "union_id", name = "union_id", example = "union_id")
	@JsonProperty(value = "union_id")
	private String unionId;

	@ApiModelProperty(value = "open_id", name = "open_id", example = "open_id")
	@JsonProperty(value = "open_id")
	private String openId;

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@Override
	public String toString() {
		return "RegisteredReq{" + "username='" + userName + '\'' + ", phone='" + phone + '\'' + ", unionId='" + unionId
				+ '\'' + ", openId='" + openId + '\'' + '}';
	}
}
