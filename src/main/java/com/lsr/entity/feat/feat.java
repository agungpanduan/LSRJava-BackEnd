package com.lsr.entity.feat;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lsr.entity.*;
import com.lsr.entity.menufeat.menufeat;
import com.lsr.entity.system.system;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "tb_m_feature")
public class feat extends BaseEntity{
	@Id
	@Column(name="feature_cd")
	private String featureCd;
	
	@Column(name = "feature_name")
	private String featureName;
	
	@Column(name = "feature_desc")
	private String featureDesc;
	
	//@OneToMany(mappedBy="featureMapp") //di forign key harus mengarah ke mappedBy
	//private List<menufeat> listMenuFeatFeat;
	
	public feat() {
		this.featureCd = featureCd;
	}

}
