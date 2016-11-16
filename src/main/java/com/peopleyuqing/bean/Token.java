package com.peopleyuqing.bean;

/**
 * Created by zhuxt on 2016/11/10.
 */
public class Token {

	//是否成功
	private Boolean success;
	//错误日志
	private String error;
	//用户名称
	private String userName;
	//项目代码
	private String proKey;
	//随机生成的uuid
	private String token;

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

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
