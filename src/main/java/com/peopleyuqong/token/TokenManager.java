package com.peopleyuqong.token;

import com.peopleyuqong.bean.Token;

/**
 * Created by zhuxt on 2016/11/10.
 */
public interface TokenManager {

	/**
	 * 创建一个用户特有的token
	 * @param userName
	 * @param proKey
	 * @return
	 */
	Token createToken(String userName, String proKey) throws Exception;

	/**
	 * 检查token是否有效
	 * @param token
	 * @return
	 */
	boolean checkToken(Token token) throws Exception;

	/**
	 * 从加密的字符串中解析token
	 * @param authentication
	 * @return
	 */
	Token decodeToken(String authentication);

	/**
	 * 清除token
	 * @param userName
	 * @param proKey
	 */
	void deleteToken(String userName, String proKey) throws Exception;

}
