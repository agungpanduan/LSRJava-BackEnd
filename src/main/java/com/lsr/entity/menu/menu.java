package com.lsr.entity.menu;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lsr.entity.*;
import com.lsr.entity.menufeat.menufeat;
import com.lsr.entity.rolemenu.rolemenu;
import com.lsr.entity.system.system;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_m_menu")
public class menu extends BaseEntity{
	@Id
	@Column(name="menu_cd")
	private String menuCd;
	
	@Column(name = "menu_name")
	private String menuName;
	
	@Column(name = "menu_desc")
	private String menuDesc;
	
	//bi-directional many-to-one association to TbMSystem
	@ManyToOne
	@JoinColumn(name="menu_type_cd")
	private system systemMapp;
	
	@Column(name = "menu_seq")
	private String menuSeq;
	
	 @Column(name = "parent_menu_cd")
	 private String parentMenuCd;
	 
	 @Column(name = "Icon")
	 private String iCon;
		
	 @Column(name = "Action")
	 private String acTion;
	 
	//bi-directional many-to-one association to TbMMenu
	//@ManyToOne
	//@JoinColumn(name="parent_menu_cd")
	//private menu menuMapp;
	
	//bi-directional many-to-one association to TbMMenu
	//@OneToMany(mappedBy="menuMapp")
	//private List<menu> listMenuParent;
	
	//bi-directional many-to-one association to TbMMenuFeat
	//@OneToMany(mappedBy="menuMapp")
	//private List<menufeat> listMenuFeatMenu;
	
	//bi-directional many-to-one association to TbMRoleMenu
	//@OneToMany(mappedBy="menuMapp")
	//private List<rolemenu> listRoleMenu;

	public menu() {}
	
	public menu(String menuCd) {
        this.menuCd = menuCd;
    }
}
