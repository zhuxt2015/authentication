package com.peopleyuqong.bean;

import java.util.Date;

/**
 * Created by zhuxt on 2016/11/14.
 */
public class User {

	private int id;
	private String username;
	private String prokey;
	private int frequency;
	private boolean visit;
	private Date start;
	private Date end;
	private int tenancy;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
