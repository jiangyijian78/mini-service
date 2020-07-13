package com.miniservice.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class SigninReq implements Serializable {

	private static final long serialVersionUID = 2725339835464243457L;

	/**
	 * 签到人手机号码
	 */
	@ApiModelProperty(value = "userId", name = "userId", example = "12")
	@JsonProperty(value = "userId")
	@NotBlank(message = "userId can not be blank")
	private Long userId;

	/**
	 * 签到时间
	 */
	@ApiModelProperty(value = "signTime", name = "signTime", example = "12345")
	@JsonProperty(value = "signTime")
	@NotNull(message = "signTime can not be blank")
	private Long signTime;

	/**
	 * 小程序唯一标识
	 */
	@ApiModelProperty(value = "open_id", name = "open_id", example = "open_id")
	@JsonProperty(value = "open_id")
	@NotBlank(message = "open id can not be blank")
	private String openId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public long getSignTime() {
		return signTime;
	}

	public void setSignTime(long signTime) {
		this.signTime = signTime;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
}
