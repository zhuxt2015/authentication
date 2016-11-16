package com.peopleyuqing.token.impl;

import com.peopleyuqing.bean.Token;
import com.peopleyuqing.bean.User;
import com.peopleyuqing.redis.RedisPool;
import com.peopleyuqing.service.User.IUserService;
import com.peopleyuqing.service.User.impl.UserServiceImpl;
import com.peopleyuqing.token.TokenManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * Created by zhuxt on 2016/11/10.
 */
@Component
public class RedisTokenManager implements TokenManager {

	private static final Logger log = LoggerFactory.getLogger(RedisTokenManager.class);

	private RedisPool pool = new RedisPool();

	@Autowired
	public IUserService userService;

	static {
		log.info("RedisTokenManager init------------------");
	}

	/**
	 * 生成token
	 * @param userName
	 * @param proKey
	 * @return
	 * @throws Exception
	 */
	@Override
	public String createToken(String userName, String proKey) throws Exception {

		log.info(userName);
		log.info(proKey);
		//验证用户是否到期
		checkPermission(userName, proKey);
		//使用uuid作为原token
		String token = UUID.randomUUID().toString().replace("-", "");
		/*Token t = new Token();
		t.setUserName(userName);
		t.setProKey(proKey);
		t.setToken(token);*/
		StringBuilder key = new StringBuilder(userName).append(proKey);
		//redis存储token
		try {
			pool.set(key.toString(), token);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("创建token失败: " + e.getMessage());
		}
		return token;
	}

	private void checkPermission(String userName, String proKey) throws Exception {
		User user = null;
		log.info(userService + "");
		user = userService.find(userName, proKey);
		if (user == null) {
			throw new Exception("用户无访问权限，请联系管理员");
		} else if (user.getEnd().before(new Date())) {
			throw new Exception("用户权限已经过期");
		}
	}

	/**
	 * 检查token是否存在
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean checkToken(Token token) throws Exception {
		checkPermission(token.getUserName(), token.getProKey());
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


	/*public static void main(String[] args) throws Exception {
		RedisTokenManager manager = new RedisTokenManager();
		Token token = manager.createToken("test", "redis");
		System.out.println("token: " + token.getToken());
	}*/

}