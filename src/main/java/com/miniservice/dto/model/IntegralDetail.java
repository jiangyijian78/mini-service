package com.miniservice.dto.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class IntegralDetail implements Serializable {

	private static final long serialVersionUID = 2909054380846484888L;

	/**
	 * 积分增量
	 */
	@ApiModelProperty(value = "integral", name = "token", example = "1")
	@JsonProperty(value = "integral")
	private Long integral;

	/**
	 * 原因
	 */
	@ApiModelProperty(value = "reason", name = "reason", example = "login")
	@JsonProperty(value = "reason")
	private String reason;

	/**
	 * 获得时间
	 */
	@ApiModelProperty(value = "time", name = "time", example = "2020-01-01 23:59:59")
	@JsonProperty(value = "time")
	private String time;

	/**
	 * 类型： 1增0减
	 */
	@ApiModelProperty(value = "type", name = "type", example = "1")
	@JsonProperty(value = "type")
	private Integer type;

	public Long getIntegral() {
		return integral;
	}

	public void setIntegral(Long integral) {
		this.integral = integral;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
