package com.lsr.vo.user;


import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfo {
	private UUID uuidusername;
	
	private String username;	
	private String email;
	
	public UserInfo(String username,String email) {
		this.username = username;
		this.email = email;
	}
	
	public UserInfo() {}
}
