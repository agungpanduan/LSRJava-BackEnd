package com.lsr.response;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse<T> {
	
	@SerializedName("Status")
    private String status;
	
	@SerializedName("Message")
    private String message;
	
    private String token;	
    private String refreshToken;    
    private String tokenExpDate;    
    private String refreshTokenExpDate;    
    private String tokenAge;    
    private String refreshTokenAge;  
    private String name;
    private String email;
    
    private T data;
    
}