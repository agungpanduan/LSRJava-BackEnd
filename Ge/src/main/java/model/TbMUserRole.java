package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tb_m_user_role database table.
 * 
 */
@Entity
@Table(name="tb_m_user_role")
@NamedQuery(name="TbMUserRole.findAll", query="SELECT t FROM TbMUserRole t")
public class TbMUserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TbMUserRolePK id;

	@Column(name="changed_by")
	private String changedBy;

	@Column(name="changed_dt")
	private Timestamp changedDt;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="created_dt")
	private Timestamp createdDt;

	//bi-directional many-to-one association to TbMRole
	@ManyToOne
	@JoinColumn(name="role_cd")
	private TbMRole tbMRole;

	//bi-directional many-to-one association to TbMUser
	@ManyToOne
	@JoinColumn(name="username")
	private TbMUser tbMUser;

	public TbMUserRole() {
	}

	public TbMUserRolePK getId() {
		return this.id;
	}

	public void setId(TbMUserRolePK id) {
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

	public TbMRole getTbMRole() {
		return this.tbMRole;
	}

	public void setTbMRole(TbMRole tbMRole) {
		this.tbMRole = tbMRole;
	}

	public TbMUser getTbMUser() {
		return this.tbMUser;
	}

	public void setTbMUser(TbMUser tbMUser) {
		this.tbMUser = tbMUser;
	}

}