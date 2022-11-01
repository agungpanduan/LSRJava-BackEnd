package com.lsr.entity.rolemenu;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class rolemenuid implements Serializable {
	private String role;
    private String menu;

    public rolemenuid() {
    }

    public rolemenuid(String role, String menu) {
        this.role = role;
        this.menu = menu;
    }
}
