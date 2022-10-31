package com.lsr.response;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegiterResponse<T> {
	
	@SerializedName("Status")
    private String status;
	
	@SerializedName("Message")
    private String message;
	
    private String token;	
    private String email;
    private String username;
    
    private T data;
    
}