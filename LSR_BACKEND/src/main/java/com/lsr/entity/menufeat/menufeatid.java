package com.lsr.entity.menufeat;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class menufeatid implements Serializable {

	private static final long serialVersionUID = 8728268133601276460L;

	private String menuCd;
	private String featureCd;
}
