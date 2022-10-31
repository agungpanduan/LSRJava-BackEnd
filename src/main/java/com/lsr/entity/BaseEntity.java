package com.lsr.entity;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {

	@Column(name = "deleted_flag")
	protected boolean deletedFlag;
	
	@Column(name = "CREATED_BY")
	protected String createBy;

	@Column(name = "CREATED_DT")
	protected Timestamp createDate;

	@Column(name = "CHANGED_BY")
	protected String changedBy;

	@Column(name = "CHANGED_DT")
	protected Timestamp changedDt;
	
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}

	public Date getChangedDt() {
		return changedDt;
	}

	public void setChangedDt(Timestamp changedDt) {
		this.changedDt = changedDt;
	}
}