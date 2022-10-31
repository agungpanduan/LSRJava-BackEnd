package com.lsr.entity.system;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lsr.entity.*;
import com.lsr.entity.menu.menu;

import lombok.Getter;
import lombok.Setter;
import com.lsr.entity.system.*;

@Getter
@Setter
@Entity
@Table(name = "tb_m_system")
public class system extends BaseEntity{
	@Id
	@Column(name = "system_cd")
	private String system_cd;
	
	//bi-directional many-to-one association to TbMSystemType
	@ManyToOne
	@JoinColumn(name="system_type_cd")
	private system systemTypeMapp;
	
	@Column(name = "system_value")
	private String system_value;
	
	@Column(name = "system_seq")
	private String system_seq;
	
	@Column(name = "system_desc")
	private String system_desc;
	
	//bi-directional many-to-one association to TbMMenu
	@OneToMany(mappedBy="systemMapp") 
	private List<menu> listMenu;
	
	public system() {}
}

