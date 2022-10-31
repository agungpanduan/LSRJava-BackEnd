package com.lsr.vo.user;

public class User {
	private String username;
	private String password;
	private String email;
	private String fullname;
	private String phone_no;
	private String jabatan;
	private String alamat;
	
	public User(String username,String password, String email, String fullname) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullname =fullname;
	}
	
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getusername() {
		return username;
	}
	public void setusername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
}
