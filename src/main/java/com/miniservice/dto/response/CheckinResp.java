package com.miniservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("check in response data")
public class CheckinResp implements Serializable {

	private static final long serialVersionUID = 7351088787904741589L;

	/**
	 * 签到结果
	 */
	@ApiModelProperty(value = "check_in_flag", name = "check_in_flag", example = "1")
	@JsonProperty(value = "check_in_flag")
	private Boolean checkInFlag;

	@ApiModelProperty(value = "message", name = "message", example = "1")
	@JsonProperty(value = "message")
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getCheckInFlag() {
		return checkInFlag;
	}

	public void setCheckInFlag(Boolean checkInFlag) {
		this.checkInFlag = checkInFlag;
	}
}
