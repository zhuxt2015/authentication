package com.peopleyuqong.bean;

/**
 * Created by zhuxt on 2016/11/10.
 */
public class Token {

	//用户名称
	private String userName;
	//项目代码
	private String proKey;
	//随机生成的uuid
	private String token;

	public Token(String userName, String proKey, String token) {
		this.userName = userName;
		this.proKey = proKey;
		this.token = token;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProKey() {
		return proKey;
	}

	public void setProKey(String proKey) {
		this.proKey = proKey;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
