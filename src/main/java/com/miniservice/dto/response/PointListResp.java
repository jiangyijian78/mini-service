package com.miniservice.dto.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.miniservice.dto.model.IntegralDetail;

import io.swagger.annotations.ApiModelProperty;

public class PointListResp implements Serializable {

	private static final long serialVersionUID = 976115071019667171L;

	@ApiModelProperty(value = "data", name = "data")
	@JsonProperty(value = "data")
	private List<IntegralDetail> data;

	public List<IntegralDetail> getData() {
		return data;
	}

	public void setData(List<IntegralDetail> data) {
		this.data = data;
	}

}
