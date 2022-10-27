package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tb_m_role_menu_feat database table.
 * 
 */
@Entity
@Table(name="tb_m_role_menu_feat")
@NamedQuery(name="TbMRoleMenuFeat.findAll", query="SELECT t FROM TbMRoleMenuFeat t")
public class TbMRoleMenuFeat implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TbMRoleMenuFeatPK id;

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

	//bi-directional many-to-one association to TbMMenuFeat
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="feature_cd", referencedColumnName="feature_cd"),
		@JoinColumn(name="menu_cd", referencedColumnName="menu_cd")
		})
	private TbMMenuFeat tbMMenuFeat;

	//bi-directional many-to-one association to TbMRole
	@ManyToOne
	@JoinColumn(name="role_cd")
	private TbMRole tbMRole;

	public TbMRoleMenuFeat() {
	}

	public TbMRoleMenuFeatPK getId() {
		return this.id;
	}

	public void setId(TbMRoleMenuFeatPK id) {
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

	public TbMMenuFeat getTbMMenuFeat() {
		return this.tbMMenuFeat;
	}

	public void setTbMMenuFeat(TbMMenuFeat tbMMenuFeat) {
		this.tbMMenuFeat = tbMMenuFeat;
	}

	public TbMRole getTbMRole() {
		return this.tbMRole;
	}

	public void setTbMRole(TbMRole tbMRole) {
		this.tbMRole = tbMRole;
	}

}