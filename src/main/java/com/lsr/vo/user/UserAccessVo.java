package com.lsr.vo.user;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAccessVo {
	//harus sama dengan nama Kolom Query Database
	private String username;
	private Long roleCd;
	private String menuCd;
	private String featureCd;
	
	private String fullName;
	private String email;
	private String roleName;
	private String menuName;
	private String menuDesc;
	private String iCon;
	private String acTion;
	
	private List<UserAccessVo> userAccessList = new ArrayList<>();
	
}