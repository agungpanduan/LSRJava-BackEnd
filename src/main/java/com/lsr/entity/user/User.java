package com.lsr.entity.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.lsr.entity.*;
import com.lsr.entity.role.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DynamicUpdate
@Table(name = "tb_m_user")
@org.hibernate.annotations.Entity(
		dynamicUpdate = true
)
public class User extends BaseEntity{
	//@Id
	//@Column(name = "USER_ID")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//private Long userId;	
	@Id
	@Column(name = "username")
	private String username;
	
	@Column(name = "fullname")
	private String fullname;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "birthday")
	private Date birthday;
	
	@Column(name = "phone_no")
	private String phone_no;
	
	@Column(name = "company")
	private String company;
	
	@Column(name = "position")
	private String position;
		
	@Column(name = "address")
	private String address;
	
	@Column(name = "path_file")
	private String path_file;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "enabled", updatable = false, nullable = false)
	private boolean enableD;
	
	@Column(name = "locked")
	private boolean lockeD;
	
	@Column(name = "facebook")
	private String facebook;
	
	@Column(name = "instagram")
	private String instagram;
	
	@Column(name = "twitter")
	private String twitter;

	@Column(name = "about")
	private String about;
	//@ManyToOne
	//@JoinColumn(name = "ROLE_ID")
	//private Role roleId;
	
//	@OneToMany(mappedBy = "username") // @OneToMany satu user dapat memiliki beberapa file
//    private List<UserFile> listuserfile = new ArrayList<>();
//	
	public User() {}	
	
	public User(String username,String password, String email, String fullname) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullname =fullname;
	}
	
}
