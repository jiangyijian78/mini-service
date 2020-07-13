package com.miniservice.aop;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.miniservice.cache.TokenSingleton;
import com.miniservice.constant.ResultEnum;
import com.miniservice.exception.ApplicationException;
import com.miniservice.exception.ParameterException;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Order(1)
public class TokenVerificationAspect {
	Logger log = LoggerFactory.getLogger(TokenVerificationAspect.class);

	@Before("@annotation(com.miniservice.annotation.TokenVerify)")
	public void signVerification(JoinPoint point) throws ApplicationException {
		log.info("Start verifying the token");
		final RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (requestAttributes != null) {
			HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
			final String token = request.getHeader("AUTH_TOKEN");
			if (StringUtils.isBlank(token)) {
				log.error("Can Not Find token from http request header");
				throw new ParameterException(ResultEnum.INVALID_TOKEN);
			}
			log.info("Get token {} from Header", token);

			// 校验token是否存在, 可用redis实现
			Optional<String> value = TokenSingleton.getInstance().findByToken(token);
			if (!value.isPresent()) {
				log.error("Token expired.");
				throw new ParameterException(ResultEnum.INVALID_TOKEN);
			}

		} else {
			log.error("RequestAttributes null.");
			throw new ParameterException(ResultEnum.INVALID_REQUEST);
		}
	}

}
