package com.miniservice.controller;

import com.miniservice.annotation.TokenVerify;
import com.miniservice.dto.ServerResponse;
import com.miniservice.dto.request.CheckinReq;
import com.miniservice.dto.request.LoginReq;
import com.miniservice.dto.request.RegisteredReq;
import com.miniservice.dto.response.*;
import com.miniservice.exception.ApplicationException;
import com.miniservice.service.MiniCheckInService;
import com.miniservice.service.MiniService;
import com.miniservice.util.RequestUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.wildfly.common.annotation.NotNull;

@RestController
@RequestMapping(path = "/mini/api/v1")
public class MiniController {

	@Autowired
	MiniService miniService;

	@Autowired
	MiniCheckInService miniCheckInService;

	@ApiOperation(value = "registered", notes = "registered", httpMethod = "POST", consumes = "application/json", response = ServerResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	@PostMapping(value = "/registered", consumes = "application/json")
	public ServerResponse<RegisteredResp> registered(@NotNull @Validated @RequestBody RegisteredReq request)
			throws ApplicationException {
		miniService.registered(request);
		return ServerResponse.createBySuccess();
	}


	@ApiOperation(value = "login", notes = "login", httpMethod = "POST", consumes = "application/json", response = ServerResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	@PostMapping(value = "/login", consumes = "application/json")
	public ServerResponse<LoginResp> login(@NotNull @Validated @RequestBody LoginReq request)
			throws ApplicationException {
		LoginResp response = miniService.loginByPassword(request);
		return ServerResponse.createBySuccess(response);
	}

	@ApiOperation(value = "checkin", notes = "checkin", httpMethod = "POST", consumes = "application/json", response = ServerResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	@PostMapping(value = "/checkin", consumes = "application/json")
    @TokenVerify
	public ServerResponse<CheckinResp> checkin(@NotNull @Validated @RequestBody CheckinReq request)
			throws ApplicationException {
		CheckinResp response = miniCheckInService.checkin(request);
		return ServerResponse.createBySuccess(response);
	}

	@ApiOperation(value = "querytotalpoint", notes = "querytotalpoint", httpMethod = "GET", consumes = "application/json", response = ServerResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	@GetMapping(value = "/querytotalpoint", consumes = "application/json")
	@TokenVerify
	public ServerResponse<PointQueryResp> queryTotalPoint() throws ApplicationException {
		String token = RequestUtil.getAuthTokenByRequest();
		PointQueryResp response = miniService.queryPoint(token);
		return ServerResponse.createBySuccess(response);
	}

	@ApiOperation(value = "querypointlist", notes = "querypointlist", httpMethod = "GET", consumes = "application/json", response = ServerResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	@GetMapping(value = "/querypointlist", consumes = "application/json")
	@TokenVerify
	public ServerResponse<PointListResp> queryPointList() throws ApplicationException {
		String token = RequestUtil.getAuthTokenByRequest();
		PointListResp response = miniService.queryPointList(token);
		return ServerResponse.createBySuccess(response);
	}

}
