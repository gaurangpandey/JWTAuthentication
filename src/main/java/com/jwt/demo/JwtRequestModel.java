package com.jwt.demo;

import org.springframework.stereotype.Component;

@Component
public class JwtRequestModel {

	private String userName;
	
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "JwtRequestModel [userName=" + userName + ", password=" + password + "]";
	}
}
