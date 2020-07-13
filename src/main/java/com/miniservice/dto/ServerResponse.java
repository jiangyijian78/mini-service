package com.miniservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.miniservice.constant.ResultEnum;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServerResponse<T> implements Serializable {

	private static final long serialVersionUID = 2175288425941550310L;

	/**
	 * 返回码
	 */
	@ApiModelProperty(value = "返回码", name = "status", example = "200")
	@JsonProperty(value = "status")
	private Integer status;

	/**
	 * 状态信息
	 */
	@ApiModelProperty(value = "状态信息", name = "status_desc", example = "success")
	@JsonProperty(value = "statusDesc")
	private String statusDesc;

	/**
	 * 数据
	 */
	@ApiModelProperty(value = "数据", name = "data", example = "success")
	@JsonProperty(value = "responseData")
	private T responseData;

	/**
	 * 通过状态码和状态信息构造对象
	 * 
	 * @param status     状态码
	 * @param statusDesc 状态信息
	 */
	public ServerResponse(int status, String statusDesc) {
		this(status, statusDesc, null);
	}

	/**
	 * 通过状态码、状态信息和返回数据构造对象
	 * 
	 * @param status       状态码
	 * @param statusDesc   状态信息
	 * @param responseData 数据
	 */
	public ServerResponse(int status, String statusDesc, T responseData) {
		this.status = status;
		this.statusDesc = statusDesc;
		this.responseData = responseData;
	}

	/**
	 * 通过状态码、状态信息和返回数据构造对象
	 * 
	 * @param statusEnum   状态枚举
	 * @param responseData 数据
	 */
	public ServerResponse(ResultEnum statusEnum, T responseData) {
		this.status = statusEnum.getCode();
		this.statusDesc = statusEnum.getMessage();
		this.responseData = responseData;
	}

	/**
	 * 通过状态枚举构造对象
	 * 
	 * @param statusEnum 状态枚举
	 */
	public ServerResponse(ResultEnum statusEnum) {
		this(statusEnum.getCode(), statusEnum.getMessage());
	}

	/**
	 * 构造固定成功响应
	 * 
	 * @return 成功响应
	 */
	public static <T> ServerResponse<T> createBySuccess() {
		return new ServerResponse<>(ResultEnum.SUCCESS);
	}

	/**
	 * 构造成功响应
	 * 
	 * @param data 返回数据
	 * @return 成功响应
	 */
	public static <T> ServerResponse<T> createBySuccess(T data) {
		return new ServerResponse<>(ResultEnum.SUCCESS, data);
	}

	/**
	 * 构造固定失败响应
	 * 
	 * @return 失败响应
	 */
	public static <T> ServerResponse<T> createByError() {
		return new ServerResponse<>(ResultEnum.SYSTEM_ERROR);
	}

	/**
	 * 构造失败响应，自定义状态码和状态信息
	 * 
	 * @param code    状态码
	 * @param message 状态信息
	 * @return 失败响应
	 */
	public static <T> ServerResponse<T> createByError(Integer code, String message) {
		return new ServerResponse<>(code, message);
	}

	/**
	 * 构造失败响应，自定义状态信息
	 * 
	 * @param message 状态信息
	 * @return 失败响应
	 */
	public static <T> ServerResponse<T> createByError(String message) {
		return new ServerResponse<>(ResultEnum.SYSTEM_ERROR.getCode(), message);
	}

	/**
	 * 根据状态枚举构造失败响应
	 * 
	 * @param responseStatusEnum 状态枚举
	 * @return 失败响应
	 */
	public static <T> ServerResponse<T> createByError(ResultEnum responseStatusEnum) {
		return new ServerResponse<>(responseStatusEnum);
	}

	/**
	 * 根据 状态枚举及返回数据 构造失败响应
	 * 
	 * @param responseStatusEnum 状态枚举
	 * @param data               返回数据
	 * @return 失败响应
	 */
	public static <T> ServerResponse<T> createByError(ResultEnum responseStatusEnum, T data) {
		return new ServerResponse<>(responseStatusEnum, data);
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public T getResponseData() {
		return responseData;
	}

	public void setResponseData(T responseData) {
		this.responseData = responseData;
	}

}
