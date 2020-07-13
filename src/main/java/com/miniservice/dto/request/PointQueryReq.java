package com.miniservice.dto.request;

import java.io.Serializable;

public class PointQueryReq implements Serializable {

	private static final long serialVersionUID = -953317547799110949L;

	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
