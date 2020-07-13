package com.miniservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InitSystemConfig implements CommandLineRunner {
	Logger logger = LoggerFactory.getLogger(InitSystemConfig.class);

	public static Map<String, String> tokenMap;

	@Override
	public void run(String... args) throws Exception {
		tokenMap = new HashMap<>();
	}

	/**
	 * 获取缓存token的value,如果使用redis，可删除此方法
	 * 
	 * @param token
	 * @return
	 */
	public static String findByToken(String token) {
		if (null == tokenMap) {
			tokenMap = new HashMap<>();
			return null;
		}

		return tokenMap.get(token);
	}

	/**
	 * 缓存token, 如果使用redis，可删除此方法
	 * 
	 * @param token
	 * @return
	 */
	public synchronized static String cacheByToken(String token, String value) {
		if (null == tokenMap) {
			tokenMap = new HashMap<>();
		}

		return tokenMap.put(token, value);
	}
}
