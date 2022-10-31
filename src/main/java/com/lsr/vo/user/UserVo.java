package com.lsr.vo.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Data @AllArgsConstructor
public class UserVo {
	private String username;
	private Long roleCd;
	private String roleName;
	
	private String fullName;
	private String email;
		
	private String gender;
	
	private Date birthday;
	private String phone_no;
	private String company;
	private String position;
	private String address;
	private String path_file;
	private int enableD;
	private int lockeD;
	private String facebook;
	private String instagram;
	private String twitter;
	private String about;
	private String ImageData;
	
	private List<UserAccessVo> userAccessList = new ArrayList<>();
	
	public UserVo() {}
}
