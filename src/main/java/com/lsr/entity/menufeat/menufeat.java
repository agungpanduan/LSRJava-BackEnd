package com.lsr.entity.menufeat;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lsr.entity.*;
import com.lsr.entity.feat.feat;
import com.lsr.entity.menu.menu;
import com.lsr.entity.rolemenufeat.rolemenufeat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_m_menu_feat")
@IdClass(menufeatid.class)
public class menufeat extends BaseEntity{
	@Id
	@Column(name = "menu_cd")
	private String menuCd;
	@Id
	@Column(name = "feature_cd")
	private String featureCd;
}