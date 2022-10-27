package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tb_m_menu database table.
 * 
 */
@Entity
@Table(name="tb_m_menu")
@NamedQuery(name="TbMMenu.findAll", query="SELECT t FROM TbMMenu t")
public class TbMMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="menu_cd")
	private String menuCd;

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

	@Column(name="menu_desc")
	private String menuDesc;

	@Column(name="menu_name")
	private String menuName;

	@Column(name="menu_seq")
	private Integer menuSeq;

	//bi-directional many-to-one association to TbMMenu
	@ManyToOne
	@JoinColumn(name="parent_menu_cd")
	private TbMMenu tbMMenu;

	//bi-directional many-to-one association to TbMMenu
	@OneToMany(mappedBy="tbMMenu")
	private List<TbMMenu> tbMMenus;

	//bi-directional many-to-one association to TbMSystem
	@ManyToOne
	@JoinColumn(name="menu_type_cd")
	private TbMSystem tbMSystem;

	//bi-directional many-to-one association to TbMMenuFeat
	@OneToMany(mappedBy="tbMMenu")
	private List<TbMMenuFeat> tbMMenuFeats;

	//bi-directional many-to-one association to TbMRoleMenu
	@OneToMany(mappedBy="tbMMenu")
	private List<TbMRoleMenu> tbMRoleMenus;

	public TbMMenu() {
	}

	public String getMenuCd() {
		return this.menuCd;
	}

	public void setMenuCd(String menuCd) {
		this.menuCd = menuCd;
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

	public String getMenuDesc() {
		return this.menuDesc;
	}

	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public Integer getMenuSeq() {
		return this.menuSeq;
	}

	public void setMenuSeq(Integer menuSeq) {
		this.menuSeq = menuSeq;
	}

	public TbMMenu getTbMMenu() {
		return this.tbMMenu;
	}

	public void setTbMMenu(TbMMenu tbMMenu) {
		this.tbMMenu = tbMMenu;
	}

	public List<TbMMenu> getTbMMenus() {
		return this.tbMMenus;
	}

	public void setTbMMenus(List<TbMMenu> tbMMenus) {
		this.tbMMenus = tbMMenus;
	}

	public TbMMenu addTbMMenus(TbMMenu tbMMenus) {
		getTbMMenus().add(tbMMenus);
		tbMMenus.setTbMMenu(this);

		return tbMMenus;
	}

	public TbMMenu removeTbMMenus(TbMMenu tbMMenus) {
		getTbMMenus().remove(tbMMenus);
		tbMMenus.setTbMMenu(null);

		return tbMMenus;
	}

	public TbMSystem getTbMSystem() {
		return this.tbMSystem;
	}

	public void setTbMSystem(TbMSystem tbMSystem) {
		this.tbMSystem = tbMSystem;
	}

	public List<TbMMenuFeat> getTbMMenuFeats() {
		return this.tbMMenuFeats;
	}

	public void setTbMMenuFeats(List<TbMMenuFeat> tbMMenuFeats) {
		this.tbMMenuFeats = tbMMenuFeats;
	}

	public TbMMenuFeat addTbMMenuFeat(TbMMenuFeat tbMMenuFeat) {
		getTbMMenuFeats().add(tbMMenuFeat);
		tbMMenuFeat.setTbMMenu(this);

		return tbMMenuFeat;
	}

	public TbMMenuFeat removeTbMMenuFeat(TbMMenuFeat tbMMenuFeat) {
		getTbMMenuFeats().remove(tbMMenuFeat);
		tbMMenuFeat.setTbMMenu(null);

		return tbMMenuFeat;
	}

	public List<TbMRoleMenu> getTbMRoleMenus() {
		return this.tbMRoleMenus;
	}

	public void setTbMRoleMenus(List<TbMRoleMenu> tbMRoleMenus) {
		this.tbMRoleMenus = tbMRoleMenus;
	}

	public TbMRoleMenu addTbMRoleMenus(TbMRoleMenu tbMRoleMenus) {
		getTbMRoleMenus().add(tbMRoleMenus);
		tbMRoleMenus.setTbMMenu(this);

		return tbMRoleMenus;
	}

	public TbMRoleMenu removeTbMRoleMenus(TbMRoleMenu tbMRoleMenus) {
		getTbMRoleMenus().remove(tbMRoleMenus);
		tbMRoleMenus.setTbMMenu(null);

		return tbMRoleMenus;
	}

}