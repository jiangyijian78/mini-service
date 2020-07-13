package com.miniservice.service;

import com.miniservice.dto.request.CheckinReq;
import com.miniservice.dto.response.CheckinResp;
import com.miniservice.exception.ApplicationException;

public interface MiniCheckInService {


	CheckinResp checkin(CheckinReq request) throws ApplicationException;

}
