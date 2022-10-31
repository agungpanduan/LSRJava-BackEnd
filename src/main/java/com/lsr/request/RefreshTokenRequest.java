package com.lsr.request;

import com.lsr.request.register.RegistrationRequest;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RefreshTokenRequest {

	private String refreshToken;
    private String username;    

}