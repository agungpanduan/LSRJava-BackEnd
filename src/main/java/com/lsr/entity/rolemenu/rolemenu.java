package com.lsr.entity.rolemenu;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lsr.entity.*;
import com.lsr.entity.menu.menu;
import com.lsr.entity.role.role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "TB_M_ROLE_MENU")
@IdClass(rolemenuid.class)
public class rolemenu extends BaseEntity{
	@Id
    @Column(name = "role_cd")
    private String role;

    @Id
    @Column(name = "menu_cd")
    private String menu;
	
	@Column(name = "full_access_flag")
	private boolean full_access_flag;
	
	 public rolemenu() {
	 
	 }

	 public rolemenu(String role, String menu) {
	        this.role = role;
	        this.menu = menu;
	 }

}
