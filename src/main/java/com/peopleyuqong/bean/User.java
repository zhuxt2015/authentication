package com.peopleyuqong.bean;

import java.util.Date;

/**
 * Created by zhuxt on 2016/11/14.
 */
public class User {

	//用户名
	private String username;
	//项目代码
	private String prokey;
	//访问频次
	private int frequency;
	//是否有访问权限
	private boolean visit;
	//权限开启时间
	private Date start;
	//权限结束时间
	private Date end;
    //权限开启天数
	private int tenancy;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProkey() {
		return prokey;
	}

	public void setProkey(String prokey) {
		this.prokey = prokey;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public boolean isVisit() {
		return visit;
	}

	public void setVisit(boolean visit) {
		this.visit = visit;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public int getTenancy() {
		return tenancy;
	}

	public void setTenancy(int tenancy) {
		this.tenancy = tenancy;
	}
}
