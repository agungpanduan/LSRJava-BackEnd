package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tb_m_menu_feat database table.
 * 
 */
@Entity
@Table(name="tb_m_menu_feat")
@NamedQuery(name="TbMMenuFeat.findAll", query="SELECT t FROM TbMMenuFeat t")
public class TbMMenuFeat implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TbMMenuFeatPK id;

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

	//bi-directional many-to-one association to TbMFeature
	@ManyToOne
	@JoinColumn(name="feature_cd")
	private TbMFeature tbMFeature;

	//bi-directional many-to-one association to TbMMenu
	@ManyToOne
	@JoinColumn(name="menu_cd")
	private TbMMenu tbMMenu;

	//bi-directional many-to-one association to TbMRoleMenuFeat
	@OneToMany(mappedBy="tbMMenuFeat")
	private List<TbMRoleMenuFeat> tbMRoleMenuFeats;

	public TbMMenuFeat() {
	}

	public TbMMenuFeatPK getId() {
		return this.id;
	}

	public void setId(TbMMenuFeatPK id) {
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

	public TbMFeature getTbMFeature() {
		return this.tbMFeature;
	}

	public void setTbMFeature(TbMFeature tbMFeature) {
		this.tbMFeature = tbMFeature;
	}

	public TbMMenu getTbMMenu() {
		return this.tbMMenu;
	}

	public void setTbMMenu(TbMMenu tbMMenu) {
		this.tbMMenu = tbMMenu;
	}

	public List<TbMRoleMenuFeat> getTbMRoleMenuFeats() {
		return this.tbMRoleMenuFeats;
	}

	public void setTbMRoleMenuFeats(List<TbMRoleMenuFeat> tbMRoleMenuFeats) {
		this.tbMRoleMenuFeats = tbMRoleMenuFeats;
	}

	public TbMRoleMenuFeat addTbMRoleMenuFeat(TbMRoleMenuFeat tbMRoleMenuFeat) {
		getTbMRoleMenuFeats().add(tbMRoleMenuFeat);
		tbMRoleMenuFeat.setTbMMenuFeat(this);

		return tbMRoleMenuFeat;
	}

	public TbMRoleMenuFeat removeTbMRoleMenuFeat(TbMRoleMenuFeat tbMRoleMenuFeat) {
		getTbMRoleMenuFeats().remove(tbMRoleMenuFeat);
		tbMRoleMenuFeat.setTbMMenuFeat(null);

		return tbMRoleMenuFeat;
	}

}