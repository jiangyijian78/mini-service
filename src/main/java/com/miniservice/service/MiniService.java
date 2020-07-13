package com.miniservice.service;

import com.miniservice.dto.request.LoginReq;
import com.miniservice.dto.request.RegisteredReq;
import com.miniservice.dto.response.LoginResp;
import com.miniservice.dto.response.PointListResp;
import com.miniservice.dto.response.PointQueryResp;
import com.miniservice.dto.response.RegisteredResp;
import com.miniservice.exception.ApplicationException;

public interface MiniService {

	public RegisteredResp registered(RegisteredReq request) throws ApplicationException;
	
	public LoginResp loginByPassword(LoginReq request) throws ApplicationException;

	public PointQueryResp queryPoint(String token) throws ApplicationException;
	
	public PointListResp queryPointList(String token) throws ApplicationException;
}
