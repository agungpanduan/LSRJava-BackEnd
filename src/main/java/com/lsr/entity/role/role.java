package com.lsr.entity.role;

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

import com.lsr.entity.*;
import com.lsr.entity.rolemenu.*;
import com.lsr.entity.rolemenufeat.*;
import com.lsr.entity.user.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_m_role")
public class role extends BaseEntity{
	@Id
	@Column(name="role_cd")
	private Long roleCd;
	
	@Column(name = "role_name")
	private String roleName;
	
	@Column(name = "role_desc")
	private String roleDesc;
	
	//bi-directional many-to-one association to TbMRoleMenu
	//@OneToMany(mappedBy="roleMapp")
	//private List<rolemenu> listRoleMenuRole;

	//bi-directional many-to-one association to TbMRoleMenuFeat
	//@OneToMany(mappedBy="roleMapp")
	//private List<rolemenufeat> listRoleMenuFeatRole;

	//bi-directional many-to-one association to TbMUserRole
	//@OneToMany(mappedBy="roleMapp")
	//private List<UserRole> listUserRoleRole;
	
	public role() {}
	
	public role(Long roleCd) {
        this.roleCd = roleCd;
    }
    public role(Long roleCd, String roleName, String roleDesc) {
        this.roleCd = roleCd;
        this.roleName = roleName;
        this.roleDesc = roleDesc;
    }
}