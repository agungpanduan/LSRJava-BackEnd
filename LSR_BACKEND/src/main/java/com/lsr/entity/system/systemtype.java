package com.lsr.entity.system;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lsr.entity.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_m_system_type")
public class systemtype extends BaseEntity{
	@Id
	@Column(name="system_type_cd")
	private String systemTypeCd;
	
	@Column(name = "system_type_name")
	private String systemTypeName;
	
	@Column(name = "system_type_desc")
	private String systemTypeDesc;
	
	//bi-directional many-to-one association to TbMSystem
	@OneToMany(mappedBy="systemTypeMapp")
	private List<system> listSytem; //system type akan di buat detailnya di sistem
	
	public systemtype() {}
}