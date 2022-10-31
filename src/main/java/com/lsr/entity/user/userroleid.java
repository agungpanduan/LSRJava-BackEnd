package com.lsr.entity.user;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class userroleid implements Serializable {
	private String username;
    private String role;

    public userroleid() {
    }

    public userroleid(String username, String role) {
        this.role = role;
        this.username = username;
    }

}
