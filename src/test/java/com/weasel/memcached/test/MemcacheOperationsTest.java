package com.weasel.memcached.test;

import com.weasel.memcached.MemcacheOperations;
import com.weasel.memcached.MemcacheRepository;

/**
 * @author Dylan
 * @time 2013-9-3
 */
public class MemcacheOperationsTest {

	public static void main(String[] args) {
		MemcacheRepository repository = new MemcacheOperations();
		User user = new User("dylan", "abc123");
		repository.save(user.getUsername(), user);
		user = repository.get(user.getUsername());
		System.out.println(user);
	}
}
