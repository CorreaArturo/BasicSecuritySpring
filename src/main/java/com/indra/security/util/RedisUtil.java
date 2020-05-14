package com.indra.security.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public enum RedisUtil
{
    INSTANCE;

    private final JedisPool pool;

    RedisUtil() 
    {
        pool = new JedisPool(new JedisPoolConfig(), "localhost");
    }
    
    public Long deleteToken(String userName) 
    {
        Jedis jedis = null;
        try
        {
            jedis = pool.getResource();
            return jedis.del(userName);
        }
        finally 
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }      	
    }
    
    public void addSimpleValue(String key, String value) 
    {
        Jedis jedis = null;
        try
        {
            jedis = pool.getResource();
            //jedis.sadd(key, value);
            jedis.set(key, value);
        } 
        finally 
        {
            if (jedis != null) 
            {
                jedis.close();
            }
        }    	
    }
    
    public boolean existsToken(String key) 
    {
        Jedis jedis = null;
        try
        {
            jedis = pool.getResource();
            return jedis.get(key) != null;
        }
        finally 
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }    	
    }
    
}
