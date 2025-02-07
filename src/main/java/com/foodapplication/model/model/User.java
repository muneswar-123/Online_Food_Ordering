package com.foodapplication.model.model;


public class User {
	
	private int userid;
	private String username;
	private String email;
	private String password;
	private long mobile;
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	
	public User(int userid, String username, String email, String password, long mobile) {
		super();
		this.userid = userid;
		this.username = username;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
	}
	
	public User(String username, String email, String password, long mobile) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
	}
	
	public User() {
		super();
	}
	
	@Override
	public String toString() {
		return userid + "   " + username + "   " + email + "   " + password+ "   " + mobile ;
	}
}