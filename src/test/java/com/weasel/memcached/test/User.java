package com.weasel.memcached.test;

import java.io.Serializable;

/**
 * @author Dylan
 * @time 2013-8-6
 */
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4065530523304239775L;

	private String username;
	
	private String password;
	
	public User(){}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "name : " + getUsername() + " password : " + getPassword();
	}
	
	
}
