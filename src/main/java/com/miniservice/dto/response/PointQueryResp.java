package com.miniservice.dto.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class PointQueryResp implements Serializable {

	private static final long serialVersionUID = -7353739539284482453L;

	@ApiModelProperty(value = "integral", name = "integral", example = "1")
	@JsonProperty(value = "integral")
	private Long integral;

	public PointQueryResp(Long integral) {
		this.integral = integral;
	}

	public Long getIntegral() {
		return integral;
	}

	public void setIntegral(Long integral) {
		this.integral = integral;
	}

	@Override
	public String toString() {
		return "PointQueryResp{" + "integral=" + integral + '}';
	}
}
