package com.peopleyuqing.servlet;

import com.peopleyuqing.redis.RedisPool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Created by zhuxt on 2016/11/11.
 */
public class Startup extends HttpServlet {
	RedisPool redisPool = new RedisPool();
	@Override
	public void init() throws ServletException {
		super.init();
		redisPool.getPool();
	}

	@Override
	public void destroy() {
		super.destroy();
		System.out.println("销毁redis连接池");
		redisPool.getPool().destroy();
	}
}
