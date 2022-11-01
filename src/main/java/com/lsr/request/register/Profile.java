package com.lsr.request.register;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Profile {
	
	private String username;
	private String password;
	private String fullname;
	private String email;
	private String gender;
	private String birthday;
	private String phone_no;
	private String company;
	private String position;
	private String address;
	private String facebook;
	private String instagram;
	private String twitter;
	private String about;
	private String path_file;
	
	private List<TFile> listFile = new ArrayList<>();
	
	public Profile() {
		// TODO Auto-generated constructor stub
	}

}
