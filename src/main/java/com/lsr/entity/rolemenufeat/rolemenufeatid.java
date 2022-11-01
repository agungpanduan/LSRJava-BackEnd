package com.lsr.entity.rolemenufeat;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class rolemenufeatid implements Serializable {
	private String roleCd;
	private String menuCd;
	private String featureCd;

	public rolemenufeatid(String roleCd, String menuCd, String featureCd) {
	    this.roleCd = roleCd;
	    this.menuCd = menuCd;
	    this.featureCd = featureCd;
	}

	public rolemenufeatid() {
	
	}
}
