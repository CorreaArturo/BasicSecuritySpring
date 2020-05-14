package com.indra.security.model.service;
/**
 * CacheService
 * @author INDRA
 * @sice 11/05/2020
 */
public interface ICacheService 
{
	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void addSimpleValue(String key, Object value);
	/**
	 * 
	 * @param key
	 * @return
	 */
	public boolean existsValue(String key);
	/**
	 * 
	 * @param key
	 * @return
	 */
	public Long deleteValue(String key);
}
