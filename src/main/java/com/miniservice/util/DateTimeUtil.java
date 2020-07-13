package com.miniservice.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期时间工具类
 */
public class DateTimeUtil {
	static Logger logger = LoggerFactory.getLogger(DateTimeUtil.class);

	private DateTimeUtil() {
	}

	/**
	 * 转换时间格式为字符串
	 * 
	 * @param dateTime 时间
	 * @param format   格式
	 * @return
	 */
	public static String formatDateTime2String(Date dateTime, String format) {
		if (null == dateTime) {
			logger.error("formatDateTime2String error: dateTime is null.");
			return null;
		}

		if (StringUtils.isBlank(format)) {
			logger.error("formatDateTime2String error: format is null.");
			return null;
		}

		try {
			Instant instant = dateTime.toInstant();
			ZoneId zone = ZoneId.systemDefault();
			LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
			return localDateTime.format(dateTimeFormatter);
		} catch (Exception e) {
			return null;
		}
	}

}
