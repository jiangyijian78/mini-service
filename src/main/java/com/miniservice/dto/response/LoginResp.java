package com.miniservice.dto.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class LoginResp implements Serializable {

	private static final long serialVersionUID = -5963688327174394093L;

	/**
	 * token
	 */
	@ApiModelProperty(value = "token", name = "token", example = "k38i9xdk48idsk109odsk8i451kdi")
	@JsonProperty(value = "token")
	private String authToken;

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
}
