package com.cn.hnust.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.JedisPool;

@Repository("redisDao")
public class RedisDaoImpl {
	@Autowired
	private JedisPool jedisPool;

	public String get(String key) {
		return jedisPool.getResource().get(key);
	}

	public String set(String key, String value) {
		return jedisPool.getResource().set(key, value);
	}

	public String hget(String hkey, String key) {
		return jedisPool.getResource().hget(hkey, key);
	}

	public long hset(String hkey, String key, String value) {
		return jedisPool.getResource().hset(hkey, key, value);
	}

}
