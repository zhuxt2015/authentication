package com.peopleyuqong.redis;

import com.peopleyuqong.utils.ReadConfigUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by zhuxt on 2016/11/10.
 */
public class RedisPool {

	private static JedisPool pool = null;
	//连接池的最大redis实例数，-1为不限制
	private static int MAXACTIVE = Integer.parseInt(ReadConfigUtil.getValue("redis.maxTotal"));
	//最多空闲的redis实例
	private static int MAXIDLE = Integer.parseInt(ReadConfigUtil.getValue("redis.maxIdle"));
	//获取redis实例的最大的超时时间
	private static int MAXWAITE = Integer.parseInt(ReadConfigUtil.getValue("redis.maxWait"));
	//redis主机
	private static String HOST = ReadConfigUtil.getValue("redis.host");
	//redis端口
	private static int PORT = Integer.parseInt(ReadConfigUtil.getValue("redis.port"));
    //key的存活时间
    private static int LIVETIME = Integer.parseInt(ReadConfigUtil.getValue("redis.livetime"));

	public RedisPool() {}

	public synchronized static JedisPool getPool() {
		if (pool == null) {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(MAXACTIVE);
			config.setMaxIdle(MAXIDLE);
			config.setMaxWaitMillis(MAXWAITE);
			//保证提供的redis实例都是可用的
			config.setTestOnBorrow(true);
			pool = new JedisPool(config, HOST, PORT);
		}
		return pool;
	}

	public static String getValue(String key) throws Exception {
		String value = null;
		Jedis jedis = null;

		if (null != key && !key.isEmpty()) {
			try {
				pool = getPool();
				jedis = pool.getResource();
				value = jedis.get(key);
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("redis无法获取token" + e.getMessage());
			}finally {
				if (jedis != null) {
					jedis.close();
				}
			}
		}
		return value;
	}

	/**
	 * 添加key, value,并设置存活时间
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean set(String key, String value) throws Exception{
		Jedis jedis = null;
		boolean result = false;
		try {
			pool = getPool();
			jedis = pool.getResource();
			jedis.set(key, value);
			jedis.expire(key, LIVETIME);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("redis无法存储token:" + e.getMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return result;
	}

	public static void delete(String key) throws Exception {
		Jedis jedis = null;
		try {
			pool = getPool();
			jedis = pool.getResource();
			jedis.del(key);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("redis 无法删除token" + e.getMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		RedisPool redisPool = new RedisPool();
		for (int i = 0; i < 10; i++) {
			redisPool.set("a", "a");
			System.out.println(redisPool.getValue("a"));
		}

	}
}
