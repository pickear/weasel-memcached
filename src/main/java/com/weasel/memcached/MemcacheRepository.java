package com.weasel.memcached;

import java.util.Date;
import java.util.Set;

/**
 * @author Dylan
 * @time 2013-8-12
 */
public interface MemcacheRepository {

	/**
	 * 从memcache中得到的是一个json格式的字符串，转化为想要的实体
	 */
	<T> T get(String key) ;

	/**
	 * 为了不同类型的转换，保存的时候以json格式保存到memcache,默认12小时后自动从memcache删除
	 * @param key
	 * @param entity
	 */
	<T> void save(String key,T entity) ;
	/**
	 * 
	 * @param key
	 * @param entity
	 * @param autoRemoveTime
	 */
	<T> void save(String key,T entity,Date autoRemoveTime);

	/**
	 * 为了不同类型的转换，保存的时候以json格式保存到memcache，默认12小时后自动从memcache删除
	 * @param key
	 * @param entity
	 */
	<T> void add(String key,T entity);
	/**
	 * 
	 * @param key
	 * @param entity
	 * @param date
	 */
	<T> void add(String key,T entity,Date autoRemoveTime);

	/**
	 * 根据标识，从memcache中删除
	 */
	void remove(String key);

	/**
	 * 为了不同类型的转换，保存的时候以json格式保存到memcache ，默认12小时后自动从memcache删除
	 * @param key
	 * @param entity
	 */
	<T> void update(String key,T entity) ;
	
	/**
	 * 
	 * @param key
	 * @param entity
	 * @param autoRemoveTime
	 */
	<T> void update(String key,T entity,Date autoRemoveTime);
	
	/**
	 * 获得memcache所有key
	 * @return
	 */
	Set<String> keys();
	
	/**
	 * 
	 */
	void destroy();
}
