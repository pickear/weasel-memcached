package com.weasel.memcached.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * @author Dylan
 * 
 */
public final class MemcachePropertiesReader {

	private static Properties properties = new Properties();
	
	private final static String DEFAULT_PROPERTIES = "/mcache.properties";
	
	static{
		InputStream is = MemcachePropertiesReader.class.getResourceAsStream("/memcache.properties");
		if (null == is) {
			is = MemcachePropertiesReader.class.getResourceAsStream(DEFAULT_PROPERTIES);
		}
		try {
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private MemcachePropertiesReader() {
	}

	public static String getProperty(String keyName) {
		return properties.getProperty(keyName);
	}
/*
	public static synchronized MemcachePropertiesReader newInstance() {
		if(null == myself){
			InputStream is = MemcachePropertiesReader.class.getResourceAsStream("/memcache.properties");
			if (null == is) {
				is = MemcachePropertiesReader.class.getResourceAsStream(DEFAULT_PROPERTIES);
			}
			try {
				properties.load(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
			myself = new MemcachePropertiesReader();
		}
		return myself;
	}*/
}
