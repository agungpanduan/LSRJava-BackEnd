package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tb_m_role_menu_feat database table.
 * 
 */
@Embeddable
public class TbMRoleMenuFeatPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="role_cd", insertable=false, updatable=false)
	private Long roleCd;

	@Column(name="menu_cd", insertable=false, updatable=false)
	private String menuCd;

	@Column(name="feature_cd", insertable=false, updatable=false)
	private String featureCd;

	public TbMRoleMenuFeatPK() {
	}
	public Long getRoleCd() {
		return this.roleCd;
	}
	public void setRoleCd(Long roleCd) {
		this.roleCd = roleCd;
	}
	public String getMenuCd() {
		return this.menuCd;
	}
	public void setMenuCd(String menuCd) {
		this.menuCd = menuCd;
	}
	public String getFeatureCd() {
		return this.featureCd;
	}
	public void setFeatureCd(String featureCd) {
		this.featureCd = featureCd;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TbMRoleMenuFeatPK)) {
			return false;
		}
		TbMRoleMenuFeatPK castOther = (TbMRoleMenuFeatPK)other;
		return 
			this.roleCd.equals(castOther.roleCd)
			&& this.menuCd.equals(castOther.menuCd)
			&& this.featureCd.equals(castOther.featureCd);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.roleCd.hashCode();
		hash = hash * prime + this.menuCd.hashCode();
		hash = hash * prime + this.featureCd.hashCode();
		
		return hash;
	}
}