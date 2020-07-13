package com.miniservice.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TokenSingleton {
	private static volatile TokenSingleton instance = null;
	private static Map<String, String> tokenMap = new HashMap<>();

	private TokenSingleton() {
	}

	public static synchronized TokenSingleton getInstance() {
		if (instance == null) {
			instance = new TokenSingleton();
		}
		return instance;
	}

	public Object readResolve() {
		return instance;
	}

	/**
	 * 获取缓存token的value
	 * 
	 * @param token
	 * @return
	 */
	public Optional<String> findByToken(String token) {
		return Optional.ofNullable(tokenMap.get(token));
	}

	/**
	 * 缓存token
	 * 
	 * @param token
	 * @return
	 */
	public synchronized String cacheByToken(String token, String value) {
		return tokenMap.put(token, value);
	}
}
