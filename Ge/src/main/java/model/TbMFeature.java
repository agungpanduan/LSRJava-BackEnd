package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tb_m_feature database table.
 * 
 */
@Entity
@Table(name="tb_m_feature")
@NamedQuery(name="TbMFeature.findAll", query="SELECT t FROM TbMFeature t")
public class TbMFeature implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="feature_cd")
	private String featureCd;

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

	@Column(name="feature_desc")
	private String featureDesc;

	@Column(name="feature_name")
	private String featureName;

	//bi-directional many-to-one association to TbMMenuFeat
	@OneToMany(mappedBy="tbMFeature")
	private List<TbMMenuFeat> tbMMenuFeats;

	public TbMFeature() {
	}

	public String getFeatureCd() {
		return this.featureCd;
	}

	public void setFeatureCd(String featureCd) {
		this.featureCd = featureCd;
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

	public String getFeatureDesc() {
		return this.featureDesc;
	}

	public void setFeatureDesc(String featureDesc) {
		this.featureDesc = featureDesc;
	}

	public String getFeatureName() {
		return this.featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public List<TbMMenuFeat> getTbMMenuFeats() {
		return this.tbMMenuFeats;
	}

	public void setTbMMenuFeats(List<TbMMenuFeat> tbMMenuFeats) {
		this.tbMMenuFeats = tbMMenuFeats;
	}

	public TbMMenuFeat addTbMMenuFeat(TbMMenuFeat tbMMenuFeat) {
		getTbMMenuFeats().add(tbMMenuFeat);
		tbMMenuFeat.setTbMFeature(this);

		return tbMMenuFeat;
	}

	public TbMMenuFeat removeTbMMenuFeat(TbMMenuFeat tbMMenuFeat) {
		getTbMMenuFeats().remove(tbMMenuFeat);
		tbMMenuFeat.setTbMFeature(null);

		return tbMMenuFeat;
	}

}