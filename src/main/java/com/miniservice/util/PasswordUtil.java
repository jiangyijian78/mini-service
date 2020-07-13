package com.miniservice.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordUtil {
	static Logger log = LoggerFactory.getLogger(PasswordUtil.class);
	/**
	 * 测试使用的账号， 生产发布时使用配置的形式
	 */
	static final String PASSWORD_HASH = "2S58RKD81@XSICM45";

	private PasswordUtil() {
	}

	/**
	 * 密码
	 * 
	 * @param password
	 * @return
	 */
	public static String passwordHash(String password) {
		return MD5Util.encodeMD5HexSalt(password, PASSWORD_HASH);
	}
}
