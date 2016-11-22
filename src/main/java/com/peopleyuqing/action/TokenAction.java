package com.peopleyuqing.action;

import com.peopleyuqing.bean.Token;
import com.peopleyuqing.token.impl.RedisTokenManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhuxt on 2016/11/16.
 */
@Controller
@RequestMapping("/token")
public class TokenAction {

	private static final Logger log = LoggerFactory.getLogger(TokenAction.class);

	@Autowired
	private RedisTokenManager manager;

	@ResponseBody
	@RequestMapping(value = "/check", produces = "application/json;charset=UTF-8")
	public Map<String,Object> check(@RequestParam(value = "username") String username,
	                 @RequestParam(value = "prokey") String prokey,
	                 @RequestParam(value = "token") String token){
		Token tokenObj = new Token();
		tokenObj.setUserName(username);
		tokenObj.setProKey(prokey);
		tokenObj.setToken(token);
		Map<String, Object> map = new ConcurrentHashMap<>();
		boolean exist = false;
		map.put("success", exist);
		map.put("error", "");
		try {
			exist = manager.checkToken(tokenObj);
			map.put("success", exist);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("error", "check error " + e.getMessage());
		}
		log.info("username: " + username + " prokey: " + prokey + " token: " + token +  " authority certification " + exist);
		return map;
	}


	@ResponseBody
	@RequestMapping(value = "/get", produces = "application/json;charset=UTF-8")
	public Token get(@RequestParam(value = "username") String username,
	                 @RequestParam(value = "prokey") String prokey) {
		Token token = new Token();
		token.setUserName(username);
		token.setProKey(prokey);
		String t;
		try {
			t = manager.createToken(username, prokey);
			token.setToken(t);
			token.setSuccess(true);
			log.info("username: " + username + " prokey: " + prokey + " get token: " + t);
		} catch (Exception e) {
			log.info("username: " + username + " prokey: " + prokey + " get token false");
			e.printStackTrace();
			token.setSuccess(false);
			token.setError(e.getMessage() + "");
		}
		return token;
	}

}
