package com.miniservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@ApiModel("check in request data")
public class CheckinReq implements Serializable {

	private static final long serialVersionUID = 2725339835464243457L;

	/**
	 * 签到人手机号码
	 */
	@ApiModelProperty(value = "phone", name = "phone", example = "13912345678")
	@JsonProperty(value = "phone")
	@Pattern(regexp = "^1[3456789]\\d{9}$", message = "Invalid phone number.")
	@NotBlank(message = "checkTime can not be blank")
	private String phone;

	/**
	 * 签到时间
	 */
	@ApiModelProperty(value = "check_time", name = "check_time", example = "12345")
	@JsonProperty(value = "check_time")
	@NotNull(message = "checkTime can not be blank")
	private Long checkInTime;

	/**
	 * 小程序唯一标识
	 */
	@ApiModelProperty(value = "form_id", name = "form_id", example = "form_id")
	@JsonProperty(value = "form_id")
	@NotBlank(message = "form id can not be blank")
	private String formId;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Long getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(Long checkInTime) {
		this.checkInTime = checkInTime;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}
}
