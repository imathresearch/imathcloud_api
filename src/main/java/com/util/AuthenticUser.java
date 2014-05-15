package com.util;

public class AuthenticUser {
	
	private String userName;
	private String secretKey;
	
	public AuthenticUser(String userName, String secretKey){
		this.userName = userName;
		this.secretKey = secretKey;
	}
	
	public String getuserName(){
		return this.userName;
	}
	
	public void setuserName(String userName){
		this.userName = userName;
	}
	
	public String getsecretKey(){
		return this.secretKey;
	}
	
	public void setsecretKey(String secretKey){
		this.secretKey = secretKey;
	}

}
