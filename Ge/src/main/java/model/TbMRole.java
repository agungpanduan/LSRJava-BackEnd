package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tb_m_role database table.
 * 
 */
@Entity
@Table(name="tb_m_role")
@NamedQuery(name="TbMRole.findAll", query="SELECT t FROM TbMRole t")
public class TbMRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="role_cd")
	private Long roleCd;

	@Column(name="changed_by")
	private String changedBy;

	@Column(name="changed_dt")
	private Timestamp changedDt;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="created_dt")
	private Timestamp createdDt;

	@Column(name="deleted_flag")
	private Boolean deletedFlag;

	@Column(name="role_desc")
	private String roleDesc;

	@Column(name="role_name")
	private String roleName;

	//bi-directional many-to-one association to TbMRoleMenu
	@OneToMany(mappedBy="tbMRole")
	private List<TbMRoleMenu> tbMRoleMenus;

	//bi-directional many-to-one association to TbMRoleMenuFeat
	@OneToMany(mappedBy="tbMRole")
	private List<TbMRoleMenuFeat> tbMRoleMenuFeats;

	//bi-directional many-to-one association to TbMUserRole
	@OneToMany(mappedBy="tbMRole")
	private List<TbMUserRole> tbMUserRoles;

	public TbMRole() {
	}

	public Long getRoleCd() {
		return this.roleCd;
	}

	public void setRoleCd(Long roleCd) {
		this.roleCd = roleCd;
	}

	public String getChangedBy() {
		return this.changedBy;
	}

	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}

	public Timestamp getChangedDt() {
		return this.changedDt;
	}

	public void setChangedDt(Timestamp changedDt) {
		this.changedDt = changedDt;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDt() {
		return this.createdDt;
	}

	public void setCreatedDt(Timestamp createdDt) {
		this.createdDt = createdDt;
	}

	public Boolean getDeletedFlag() {
		return this.deletedFlag;
	}

	public void setDeletedFlag(Boolean deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<TbMRoleMenu> getTbMRoleMenus() {
		return this.tbMRoleMenus;
	}

	public void setTbMRoleMenus(List<TbMRoleMenu> tbMRoleMenus) {
		this.tbMRoleMenus = tbMRoleMenus;
	}

	public TbMRoleMenu addTbMRoleMenus(TbMRoleMenu tbMRoleMenus) {
		getTbMRoleMenus().add(tbMRoleMenus);
		tbMRoleMenus.setTbMRole(this);

		return tbMRoleMenus;
	}

	public TbMRoleMenu removeTbMRoleMenus(TbMRoleMenu tbMRoleMenus) {
		getTbMRoleMenus().remove(tbMRoleMenus);
		tbMRoleMenus.setTbMRole(null);

		return tbMRoleMenus;
	}

	public List<TbMRoleMenuFeat> getTbMRoleMenuFeats() {
		return this.tbMRoleMenuFeats;
	}

	public void setTbMRoleMenuFeats(List<TbMRoleMenuFeat> tbMRoleMenuFeats) {
		this.tbMRoleMenuFeats = tbMRoleMenuFeats;
	}

	public TbMRoleMenuFeat addTbMRoleMenuFeat(TbMRoleMenuFeat tbMRoleMenuFeat) {
		getTbMRoleMenuFeats().add(tbMRoleMenuFeat);
		tbMRoleMenuFeat.setTbMRole(this);

		return tbMRoleMenuFeat;
	}

	public TbMRoleMenuFeat removeTbMRoleMenuFeat(TbMRoleMenuFeat tbMRoleMenuFeat) {
		getTbMRoleMenuFeats().remove(tbMRoleMenuFeat);
		tbMRoleMenuFeat.setTbMRole(null);

		return tbMRoleMenuFeat;
	}

	public List<TbMUserRole> getTbMUserRoles() {
		return this.tbMUserRoles;
	}

	public void setTbMUserRoles(List<TbMUserRole> tbMUserRoles) {
		this.tbMUserRoles = tbMUserRoles;
	}

	public TbMUserRole addTbMUserRole(TbMUserRole tbMUserRole) {
		getTbMUserRoles().add(tbMUserRole);
		tbMUserRole.setTbMRole(this);

		return tbMUserRole;
	}

	public TbMUserRole removeTbMUserRole(TbMUserRole tbMUserRole) {
		getTbMUserRoles().remove(tbMUserRole);
		tbMUserRole.setTbMRole(null);

		return tbMUserRole;
	}

}