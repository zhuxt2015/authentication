package com.peopleyuqing.redis;

import cn.peopleyuqing.builder.ClientBuilder;
import com.peopleyuqing.utils.ReadConfigUtil;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisCluster;

/**
 * Created by zhuxt on 2016/11/10.
 */
@Repository
public class RedisPool {
	//	private static JedisPool pool = null;
	private static JedisCluster redisCluster = null;
	private static GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
	private static long appId = 10051;

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
	//每次返回连接测试是否可用
	private static boolean TEST = Boolean.parseBoolean((ReadConfigUtil.getValue("redis.testOnBorrow")));
	//连接池超时时间
	private static int TIMEOUT = Integer.parseInt(ReadConfigUtil.getValue("pool.timeout"));

	static {
		/*JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(MAXACTIVE);
		config.setMaxIdle(MAXIDLE);
		config.setMaxWaitMillis(MAXWAITE);
		//保证提供的redis实例都是可用的
		config.setTestOnBorrow(TEST);
		config.setTimeBetweenEvictionRunsMillis(60000);
		pool = new JedisPool(config, HOST, PORT, TIMEOUT);*/

		redisCluster = ClientBuilder.redisCluster(appId)
				.setJedisPoolConfig(poolConfig)
				//连接超时
				.setConnectionTimeout(TIMEOUT)
				//执行超时
				.setSoTimeout(1000)
				//节点定位重试的次数
				.setMaxRedirections(5)
				//连接的是武汉的redis集群
				.build("wh");


	}

	public String getValue(String key) throws Exception {
		String value = null;

		if (null != key && !key.isEmpty()) {
			try {
				value = redisCluster.get(key);
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("redis无法获取token" + e.getMessage());
			}
		}
		return value;
	}

	/**
	 * 添加key, value,并设置存活时间
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(String key, String value) throws Exception {
		boolean result = false;
		try {
			redisCluster.setex(key,LIVETIME, value);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("redis无法存储token:" + e.getMessage());
		}
		return result;
	}




	public void delete(String key) throws Exception {
		try {
			redisCluster.del(key);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("redis 无法删除token" + e.getMessage());
		}
	}

	public static void main(String[] args) throws Exception {
//		long start = System.currentTimeMillis();
//		try {
//			for (int i = 0; i < 10; i++) {
//				RedisPool.set("a", "a");
//				System.out.println(RedisPool.getValue("a"));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println(System.currentTimeMillis() - start + "ms");
		long start = System.currentTimeMillis();
		RedisPool.redisCluster.set("11", "aaaa");
//		System.out.println(redisPool.getValue("11"));
		System.out.println(System.currentTimeMillis() - start + "ms");
	}
}
