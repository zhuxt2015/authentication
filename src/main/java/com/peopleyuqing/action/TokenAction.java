package com.peopleyuqing.action;

import com.peopleyuqing.bean.Token;
import com.peopleyuqing.token.impl.RedisTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhuxt on 2016/11/16.
 */
@Controller
@RequestMapping("/token")
public class TokenAction {

	@Autowired
	private RedisTokenManager manager;

	@ResponseBody
	@RequestMapping("/check")
	public Token get(@RequestParam(value = "username") String username,
	                      @RequestParam(value = "prokey") String prokey,
	                 @RequestParam(value = "token") String token){
		Token tokenObj = new Token();
		tokenObj.setUserName(username);
		tokenObj.setProKey(prokey);
		tokenObj.setToken(token);
		boolean exist;
		try {
			exist = manager.checkToken(tokenObj);
			tokenObj.setSuccess(exist);
		} catch (Exception e) {
			e.printStackTrace();
			tokenObj.setSuccess(false);
			tokenObj.setError(e.getMessage() + "");
		}
		return tokenObj;
	}


	@ResponseBody
	@RequestMapping("/get")
	public Token check(@RequestParam(value = "username") String username,
	                      @RequestParam(value = "prokey") String prokey){
		Token token = new Token();
		token.setUserName(username);
		token.setProKey(prokey);
		String t;
		try {
			t = manager.createToken(username, prokey);
			token.setToken(t);
			token.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			token.setSuccess(false);
			token.setError(e.getMessage() + "");
		}
		return token;
	}

}
