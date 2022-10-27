package com.lsr.entity.rolemenufeat;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
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
import com.lsr.entity.menufeat.menufeat;
import com.lsr.entity.role.role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_m_role_menu_feat")
@IdClass(rolemenufeatid.class)
public class rolemenufeat {
	@Id
	@Column(name = "ROLE_CD")
	private String roleCd;
	@Id
	@Column(name = "MENU_CD")
	private String menuCd;
	@Id
	@Column(name = "FEATURE_CD")
	private String featureCd;
}
