package com.weasel.memcached.config;

import com.weasel.memcached.util.MemcachePropertiesReader;

import com.danga.MemCached.SockIOPool;

/**
 * @author Dylan
 * @time 2013-7-19
 */
public class MemcacheConfig {

	private SockIOPool pool = null;

	{
		pool = SockIOPool.getInstance(MemcachePropertiesReader.getProperty("memcache.instanceName"));
		pool.setServers(MemcachePropertiesReader.getProperty("mcache.server").split(","));
		pool.setInitConn(Integer.valueOf(MemcachePropertiesReader.getProperty("memcache.initConn")));
		pool.setMinConn(Integer.valueOf(MemcachePropertiesReader.getProperty("memcache.minConn")));
		pool.setMaxConn(Integer.valueOf(MemcachePropertiesReader.getProperty("memcache.maxConn")));
		pool.setMaintSleep(Long.valueOf(MemcachePropertiesReader.getProperty("memcache.maintSleep")));
		pool.setSocketTO(Integer.valueOf(MemcachePropertiesReader.getProperty("memcache.socketTO")));
		pool.setNagle(Boolean.valueOf(MemcachePropertiesReader.getProperty("memcache.nagle")));
	}

	public MemcacheConfig() {

	}

	/**
	 * 
	 */
	public void initialize() {
		pool.initialize();
	}

	/**
	 * 
	 */
	public void destroy() {
		pool.shutDown();
	}
}
