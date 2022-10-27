package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tb_m_role_menu database table.
 * 
 */
@Embeddable
public class TbMRoleMenuPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="role_cd", insertable=false, updatable=false)
	private Long roleCd;

	@Column(name="menu_cd", insertable=false, updatable=false)
	private String menuCd;

	public TbMRoleMenuPK() {
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TbMRoleMenuPK)) {
			return false;
		}
		TbMRoleMenuPK castOther = (TbMRoleMenuPK)other;
		return 
			this.roleCd.equals(castOther.roleCd)
			&& this.menuCd.equals(castOther.menuCd);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.roleCd.hashCode();
		hash = hash * prime + this.menuCd.hashCode();
		
		return hash;
	}
}