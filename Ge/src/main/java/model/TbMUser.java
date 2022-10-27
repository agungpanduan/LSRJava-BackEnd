package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tb_m_user database table.
 * 
 */
@Entity
@Table(name="tb_m_user")
@NamedQuery(name="TbMUser.findAll", query="SELECT t FROM TbMUser t")
public class TbMUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String username;

	private String alamat;

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

	private String email;

	private String fullname;

	private String jabatan;

	private String pass;

	@Column(name="path_file")
	private String pathFile;

	@Column(name="phone_no")
	private String phoneNo;

	//bi-directional many-to-one association to TbMUserRole
	@OneToMany(mappedBy="tbMUser")
	private List<TbMUserRole> tbMUserRoles;

	public TbMUser() {
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAlamat() {
		return this.alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getJabatan() {
		return this.jabatan;
	}

	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPathFile() {
		return this.pathFile;
	}

	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}

	public String getPhoneNo() {
		return this.phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public List<TbMUserRole> getTbMUserRoles() {
		return this.tbMUserRoles;
	}

	public void setTbMUserRoles(List<TbMUserRole> tbMUserRoles) {
		this.tbMUserRoles = tbMUserRoles;
	}

	public TbMUserRole addTbMUserRole(TbMUserRole tbMUserRole) {
		getTbMUserRoles().add(tbMUserRole);
		tbMUserRole.setTbMUser(this);

		return tbMUserRole;
	}

	public TbMUserRole removeTbMUserRole(TbMUserRole tbMUserRole) {
		getTbMUserRoles().remove(tbMUserRole);
		tbMUserRole.setTbMUser(null);

		return tbMUserRole;
	}

}