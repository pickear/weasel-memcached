package com.weasel.memcached;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.weasel.lang.helper.DemonPredict;
import com.weasel.memcached.config.MemcacheConfig;
import com.weasel.memcached.memcached.danga.MemCachedClient;
import com.weasel.memcached.util.MemcachePropertiesReader;

/**
 * @author Dylan
 * @time 2013-7-19
 */
public class MemcacheOperations implements MemcacheRepository{


	private MemCachedClient memcache = null;
	
	private MemcacheConfig config = null;
	
	private final static Logger LOG = LoggerFactory.getLogger(MemcacheOperations.class);

	/**
	 * 
	 */
	public MemcacheOperations() {
		config = new MemcacheConfig();
		config.initialize();
		memcache = new MemCachedClient(MemcachePropertiesReader.getProperty("memcache.instanceName"));
	}


	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(String key) {
		DemonPredict.notNull(key, "key must not be null...");
		T  entity = (T) memcache.get(key);
		if(LOG.isDebugEnabled()){
			LOG.debug("get the entity is " + entity);
		}
		return entity;
	}

	/**
	 * 
	 * @param key
	 * @param entity
	 */
	public <T> void save(String key,T entity) {
		DemonPredict.notNull(entity, "entity must not be null...");
		if(LOG.isDebugEnabled()){
			LOG.debug("begin save the "+entity+" to memcache");
		}
		memcache.set(key, entity);
	}
	/**
	 * 
	 * @param key
	 * @param entity
	 * @param autoRemoveTime
	 */
	public <T> void save(String key,T entity,Date autoRemoveTime){
		DemonPredict.notNull(entity, "entity must not be null...");
		if(LOG.isDebugEnabled()){
			LOG.debug("begin save the "+entity+" to memcache");
		}
		memcache.set(key, entity,autoRemoveTime);
	}

	/**
	 * 
	 * @param key
	 * @param entity
	 */
	public <T> void add(String key,T entity) {
		DemonPredict.notNull(entity, "entity must not be null...");
		if(LOG.isDebugEnabled()){
			LOG.debug("begin add the "+entity+" to memcache");
		}
		memcache.add(key, entity);
	}
	/**
	 * 
	 * @param key
	 * @param entity
	 * @param date
	 */
	public <T> void add(String key,T entity,Date autoRemoveTime){
		DemonPredict.notNull(entity, "entity must not be null...");
		if(LOG.isDebugEnabled()){
			LOG.debug("begin add the "+entity+" to memcache");
		}
		memcache.add(key, entity, autoRemoveTime);
	}

	/**
	 * 根据标识，从memcache中删除
	 */
	public void remove(String key) {
		DemonPredict.notNull(key, "key must not be null...");
		if(LOG.isDebugEnabled()){
			LOG.debug("begin remove the "+key+" from memcache");
		}
		memcache.delete(key);
	}

	/**
	 * 
	 * @param key
	 * @param entity
	 */
	public <T> void update(String key,T entity) {
		DemonPredict.notNull(entity, "entity must not be null...");
		if(LOG.isDebugEnabled()){
			LOG.debug("begin update the "+entity+" to memcache");
		}
		memcache.replace(key, entity);
	}
	
	/**
	 * 
	 * @param key
	 * @param entity
	 * @param autoRemoveTime
	 */
	public <T> void update(String key,T entity,Date autoRemoveTime){
		DemonPredict.notNull(entity, "entity must not be null...");
		if(LOG.isDebugEnabled()){
			LOG.debug("begin update the "+entity+" to memcache");
		}
		memcache.replace(key, entity,autoRemoveTime);
	}
	
	/**
	 * 获得缓存的所有key
	 * @return
	 */
	public Set<String> keys() {
		Set<String> keys = new HashSet<String>();
		Map<String,Map<String,String>> v1 = memcache.statsItems();
		for(String k1 : v1.keySet()){
			Map<String,String> v2 = v1.get(k1);
			for(String k2 : v2.keySet()){
				String v3 = v2.get(k2);
				 if (k2.endsWith("number")) {
					 String[] arr = k2.split(":");
					 int slabNumber = Integer.valueOf(arr[1].trim());
					 int limit = Integer.valueOf(v3.trim());
					 Map<String, Map<String, String>> v11 = memcache.statsCacheDump(slabNumber, limit);
					 for(String k11 : v11.keySet()){
						 Map<String,String> v22 = v11.get(k11);
						 for(String k22 : v22.keySet()){
						//	 String key = v22.get(k22);
							 keys.add(k22);
						 }
					 }
				 }
			}
		}
		return keys;
	}

	@Override
	public void destroy() {
		config.destroy();
	}
}
