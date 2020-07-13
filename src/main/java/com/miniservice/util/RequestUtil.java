package com.miniservice.util;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class RequestUtil {
	static Logger logger = LoggerFactory.getLogger(RequestUtil.class);

	private RequestUtil() {
	}

	public static String getAuthTokenByRequest() {
		return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
				.map(attrs -> ((ServletRequestAttributes) attrs).getRequest())
				.map(request -> request.getHeader("AUTH_TOKEN")).orElseGet(() -> null);
	}

}
