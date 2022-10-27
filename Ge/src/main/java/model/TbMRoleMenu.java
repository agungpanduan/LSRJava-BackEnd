package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tb_m_role_menu database table.
 * 
 */
@Entity
@Table(name="tb_m_role_menu")
@NamedQuery(name="TbMRoleMenu.findAll", query="SELECT t FROM TbMRoleMenu t")
public class TbMRoleMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TbMRoleMenuPK id;

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

	@Column(name="full_access_flag")
	private Boolean fullAccessFlag;

	//bi-directional many-to-one association to TbMMenu
	@ManyToOne
	@JoinColumn(name="menu_cd")
	private TbMMenu tbMMenu;

	//bi-directional many-to-one association to TbMRole
	@ManyToOne
	@JoinColumn(name="role_cd")
	private TbMRole tbMRole;

	public TbMRoleMenu() {
	}

	public TbMRoleMenuPK getId() {
		return this.id;
	}

	public void setId(TbMRoleMenuPK id) {
		this.id = id;
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

	public Boolean getFullAccessFlag() {
		return this.fullAccessFlag;
	}

	public void setFullAccessFlag(Boolean fullAccessFlag) {
		this.fullAccessFlag = fullAccessFlag;
	}

	public TbMMenu getTbMMenu() {
		return this.tbMMenu;
	}

	public void setTbMMenu(TbMMenu tbMMenu) {
		this.tbMMenu = tbMMenu;
	}

	public TbMRole getTbMRole() {
		return this.tbMRole;
	}

	public void setTbMRole(TbMRole tbMRole) {
		this.tbMRole = tbMRole;
	}

}