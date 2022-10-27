package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tb_m_system_type database table.
 * 
 */
@Entity
@Table(name="tb_m_system_type")
@NamedQuery(name="TbMSystemType.findAll", query="SELECT t FROM TbMSystemType t")
public class TbMSystemType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="system_type_cd")
	private String systemTypeCd;

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

	@Column(name="system_type_desc")
	private String systemTypeDesc;

	@Column(name="system_type_name")
	private String systemTypeName;

	//bi-directional many-to-one association to TbMSystem
	@OneToMany(mappedBy="tbMSystemType")
	private List<TbMSystem> tbMSystems;

	public TbMSystemType() {
	}

	public String getSystemTypeCd() {
		return this.systemTypeCd;
	}

	public void setSystemTypeCd(String systemTypeCd) {
		this.systemTypeCd = systemTypeCd;
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

	public String getSystemTypeDesc() {
		return this.systemTypeDesc;
	}

	public void setSystemTypeDesc(String systemTypeDesc) {
		this.systemTypeDesc = systemTypeDesc;
	}

	public String getSystemTypeName() {
		return this.systemTypeName;
	}

	public void setSystemTypeName(String systemTypeName) {
		this.systemTypeName = systemTypeName;
	}

	public List<TbMSystem> getTbMSystems() {
		return this.tbMSystems;
	}

	public void setTbMSystems(List<TbMSystem> tbMSystems) {
		this.tbMSystems = tbMSystems;
	}

	public TbMSystem addTbMSystem(TbMSystem tbMSystem) {
		getTbMSystems().add(tbMSystem);
		tbMSystem.setTbMSystemType(this);

		return tbMSystem;
	}

	public TbMSystem removeTbMSystem(TbMSystem tbMSystem) {
		getTbMSystems().remove(tbMSystem);
		tbMSystem.setTbMSystemType(null);

		return tbMSystem;
	}

}