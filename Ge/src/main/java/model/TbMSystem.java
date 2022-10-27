package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tb_m_system database table.
 * 
 */
@Entity
@Table(name="tb_m_system")
@NamedQuery(name="TbMSystem.findAll", query="SELECT t FROM TbMSystem t")
public class TbMSystem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="system_cd")
	private String systemCd;

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

	@Column(name="system_desc")
	private String systemDesc;

	@Column(name="system_seq")
	private Integer systemSeq;

	@Column(name="system_value")
	private String systemValue;

	//bi-directional many-to-one association to TbMMenu
	@OneToMany(mappedBy="tbMSystem")
	private List<TbMMenu> tbMMenus;

	//bi-directional many-to-one association to TbMSystemType
	@ManyToOne
	@JoinColumn(name="system_type_cd")
	private TbMSystemType tbMSystemType;

	public TbMSystem() {
	}

	public String getSystemCd() {
		return this.systemCd;
	}

	public void setSystemCd(String systemCd) {
		this.systemCd = systemCd;
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

	public String getSystemDesc() {
		return this.systemDesc;
	}

	public void setSystemDesc(String systemDesc) {
		this.systemDesc = systemDesc;
	}

	public Integer getSystemSeq() {
		return this.systemSeq;
	}

	public void setSystemSeq(Integer systemSeq) {
		this.systemSeq = systemSeq;
	}

	public String getSystemValue() {
		return this.systemValue;
	}

	public void setSystemValue(String systemValue) {
		this.systemValue = systemValue;
	}

	public List<TbMMenu> getTbMMenus() {
		return this.tbMMenus;
	}

	public void setTbMMenus(List<TbMMenu> tbMMenus) {
		this.tbMMenus = tbMMenus;
	}

	public TbMMenu addTbMMenus(TbMMenu tbMMenus) {
		getTbMMenus().add(tbMMenus);
		tbMMenus.setTbMSystem(this);

		return tbMMenus;
	}

	public TbMMenu removeTbMMenus(TbMMenu tbMMenus) {
		getTbMMenus().remove(tbMMenus);
		tbMMenus.setTbMSystem(null);

		return tbMMenus;
	}

	public TbMSystemType getTbMSystemType() {
		return this.tbMSystemType;
	}

	public void setTbMSystemType(TbMSystemType tbMSystemType) {
		this.tbMSystemType = tbMSystemType;
	}

}