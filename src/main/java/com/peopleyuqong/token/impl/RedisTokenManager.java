package com.peopleyuqong.token.impl;

import com.peopleyuqong.bean.Token;
import com.peopleyuqong.redis.RedisPool;
import com.peopleyuqong.token.TokenManager;

import java.util.UUID;

/**
 * Created by zhuxt on 2016/11/10.
 */
public class RedisTokenManager implements TokenManager {

	private RedisPool pool = new RedisPool();

	/**
	 * 生成token
	 * @param userName
	 * @param proKey
	 * @return
	 * @throws Exception
	 */
	@Override
	public Token createToken(String userName, String proKey) throws Exception {
		//使用uuid作为原token
		String token = UUID.randomUUID().toString().replace("-", "");
		Token t = new Token(userName, proKey, token);
		StringBuilder key = new StringBuilder(userName).append(proKey);
		//redis存储token
		try {
			pool.set(key.toString(), token);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("创建token失败: " + e.getMessage());
		}
		return t;
	}

	/**
	 * 检查token是否存在
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean checkToken(Token token) throws Exception {
		boolean result = false;
		if (token != null) {
			String tokenValue = null;
			String key = token.getUserName() + token.getProKey();
			System.out.println("key: " + key);
			try {
				tokenValue = pool.getValue(key);
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("检查token失败: " + e.getMessage());
			}
			if (tokenValue != null && tokenValue.equals(token.getToken())) {
				//重新设置key value，重新计算存活时间
				pool.set(key, tokenValue);
				result = true;
			}
		}
		return result;
	}

	/**
	 * 从加密字符串解码token
	 * @param encodeString
	 * @return
	 */
	@Override
	public Token decodeToken(String encodeString) {
		Token token = null;
		String[] param = encodeString.split("_");
		if (param.length == 3 && null != encodeString && !encodeString.isEmpty()) {
			String userName = param[0];
			String proKey = param[1];
			String tokenValue = param[2];
			token.setUserName(userName);
			token.setProKey(proKey);
			token.setToken(tokenValue);
		}
		return token;
	}

	@Override
	public void deleteToken(String userName, String proKey) throws Exception {
		pool.delete(userName + proKey);
	}

	public static void main(String[] args) throws Exception {
		RedisTokenManager manager = new RedisTokenManager();
		Token token = manager.createToken("test", "redis");
		System.out.println("token: " + token.getToken());
	}
}
