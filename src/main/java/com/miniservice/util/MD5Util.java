package com.miniservice.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * MD5
 */
public class MD5Util {

	private MD5Util() {
	}

	/**
	 * MD5消息摘要
	 * 
	 * @param data 待做摘要处理的数据
	 * @return String
	 */
	public static String encodeMD5HexSalt(String data, String salt) {
		if (StringUtils.isBlank(data))
			return StringUtils.EMPTY;

		return DigestUtils.md5Hex(data + salt);
	}

}
