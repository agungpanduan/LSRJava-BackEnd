package com.lsr.vo.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLdapVo {
	
	private Integer expires_in;
	private Integer refresh_expires_in;
	
	private String access_token;	
	private String refresh_token;
	private String token_type;
	private String id_token;
	private String session_state;
	private String scope;
	private String error;
	private String error_description;
	    	
}