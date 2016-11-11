package com.peopleyuqong.utils;

import java.util.ResourceBundle;

/**
 * Created by zhuxt on 2016/11/10.
 */
public class ReadConfigUtil {

	public static String getValue(String key) {
		ResourceBundle bundle = ResourceBundle.getBundle("redis");
		return bundle.getString(key);
	}

	public static void main(String[] args) {
		System.out.println(ReadConfigUtil.getValue("host"));

	}
}
