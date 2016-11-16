package com.peopleyuqing.redis;

import redis.clients.jedis.Jedis;

/**
 * Created by zhuxt on 2016/11/10.
 */
public class ConnectRedis {
	public static void main(String[] args) {
		//连接redis服务
		Jedis jedis = new Jedis("10.38.11.14", 6379, 60000);
		System.out.println("Connection to server successfully");
		jedis.set("a", "a");
		//查看服务是否在运行
		System.out.println("Server is running: " + jedis.get("a"));
	}
}
