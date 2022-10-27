package com.lsr.request.user;

import java.util.Date;

import javax.persistence.Column;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
	private String username;
	private String fullname;
	private String email;
	private String phone_no;
	private String jabatan;
	private String alamat;
	private String path_file;
	private String pass;
	
	private Object[] roleCd;
	private String roleName;	
	private String searchValue;
	
	private Integer pageSize;	
	private Integer pageNumber;
	//@ManyToOne
	//@JoinColumn(name = "ROLE_ID")
	//private Role roleId;
}
