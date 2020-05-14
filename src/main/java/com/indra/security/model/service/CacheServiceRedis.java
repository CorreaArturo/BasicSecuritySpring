package com.indra.security.model.service;

import org.springframework.stereotype.Service;

import com.indra.security.util.RedisUtil;

/**
 * CacheServiceRedisImpl
 * @author INDRA
 * @sice 11/05/2020
 */

public class CacheServiceRedis implements ICacheService 
{

	@Override
	public void addSimpleValue(String key, Object value) 
	{
		RedisUtil.INSTANCE.addSimpleValue(key, String.valueOf(value));

	}

	@Override
	public boolean existsValue(String key) 
	{
		return RedisUtil.INSTANCE.existsToken(key);
	}

	@Override
	public Long deleteValue(String key) 
	{
		return RedisUtil.INSTANCE.deleteToken(key);
	}

}
