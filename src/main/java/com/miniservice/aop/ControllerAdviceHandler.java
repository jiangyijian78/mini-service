package com.miniservice.aop;

import com.miniservice.constant.ResultEnum;
import com.miniservice.dto.ServerResponse;
import com.miniservice.exception.ApplicationException;
import com.miniservice.exception.ParameterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice(basePackages = { "com.miniservice.controller" })
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerAdviceHandler {
	Logger log = LoggerFactory.getLogger(ControllerAdviceHandler.class);
	private static HttpHeaders headers = new HttpHeaders();

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final Object handleException(MethodArgumentNotValidException e) {
		log.error("An method ArgumentNotValidException occurs within the system : {}", e.getMessage());

		List<FieldError> errors = e.getBindingResult().getFieldErrors();
		String message = null;
		if (errors.isEmpty()) {
			message = "invalid payload";
		} else {
			message = errors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining("; "));
		}

		ServerResponse<String> response = ServerResponse.createByError();
		response.setStatus(ResultEnum.INVALID_PARAMETER.getCode());
		response.setStatusDesc(message);
		return response;
	}

	@ExceptionHandler(ParameterException.class)
	public final Object handleException(ParameterException e) {
		log.error("An ParameterException occurs within the system : {}", e.getMessage());

		ServerResponse<String> response = ServerResponse.createByError();
		response.setStatus(ResultEnum.INVALID_PARAMETER.getCode());
		response.setStatusDesc(e.getMessage());
		return new ResponseEntity<>(response, headers, HttpStatus.OK);
	}

	@ExceptionHandler(ApplicationException.class)
	public final Object handleException(ApplicationException e) {
		log.error("An ApplicationException occurs within the system : {}", e.getMessage());

		ServerResponse<String> response = ServerResponse.createByError();
		response.setStatus(ResultEnum.SYSTEM_ERROR.getCode());
		response.setStatusDesc(e.getMessage());
		return new ResponseEntity<>(response, headers, HttpStatus.OK);
	}

	@ExceptionHandler(Exception.class)
	public final Object handleTopException(Exception e) {

		log.error("An Exception occurs within the system : ", e);

		ServerResponse<String> response = new ServerResponse<>(ResultEnum.SYSTEM_ERROR.getCode(),
				"Service internal error");
		return new ResponseEntity<>(response, headers, HttpStatus.OK);
	}
}
