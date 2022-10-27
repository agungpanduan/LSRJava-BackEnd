package com.lsr.service.user;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lsr.entity.register.token.ConfirmationToken;
import com.lsr.entity.register.token.ConfirmationTokenService;
import com.lsr.entity.user.*;
import com.lsr.repository.user.*;
import com.lsr.response.RegiterResponse;

import lombok.AllArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{
		private final static String USER_NOT_FOUND_MSG =
            "user with username %s not found";
	
	private final UserRepository appUserRepository;
	
//	@Autowired   
//	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Bean
	private PasswordEncoder passwordConvertEncoder() {
		
	    return new BCryptPasswordEncoder();
	}
	
	private final ConfirmationTokenService confirmationTokenService;
		 
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    com.lsr.entity.user.User user = userRepository.findByUsername(username)
	        .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username)));

	    return UserDetailsImpl.build(user);
	}
	
	@SuppressWarnings({ "rawtypes" })
	public RegiterResponse signUpUser(User user) {
	     RegiterResponse responseApi = new RegiterResponse();
	     
	     try { 
	    	 boolean userExists = appUserRepository
		                .findByUsername(user.getUsername())
		                .isPresent();
	    	 if (userExists) {
		            // TODO check of attributes are the same and
		            // TODO if email not confirmed send confirmation email.
	    		 responseApi.setStatus("Error");
	    		 responseApi.setMessage("username already taken");
		         //token ="Error";
		            //throw new IllegalStateException("username already taken");
		      }
		     else {
		    	 String encodedPassword = passwordConvertEncoder().encode(user.getPassword());
//			        String encodedPassword = bCryptPasswordEncoder
//			                .encode(user.getPassword());
			        
			        user.setPassword(encodedPassword);
			        user.setCreateBy("Registered");
			        user.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
			        appUserRepository.save(user);

			        
			        responseApi.setToken(UUID.randomUUID().toString());
		           
			        ConfirmationToken confirmationToken = new ConfirmationToken(
			        		responseApi.getToken(),
			                LocalDateTime.now(),
			                LocalDateTime.now().plusMinutes(15),
			                user
			        );

			        confirmationTokenService.saveConfirmationToken(
			                confirmationToken);
			        
			        responseApi.setStatus("Success");
//			        TODO: SEND EMAIL

			        
		     }
	     }
	     catch(Exception e) {
	    	 responseApi.setStatus("Error");
			 responseApi.setMessage(e.getMessage());
	     }
	     
	     return responseApi;
	 }
	
//	 public String signUpUser(User user) {
//	     boolean userExists = appUserRepository
//	                .findByUsername(user.getUsername())
//	                .isPresent();
//	     String token;
//	     if (userExists) {
//	            // TODO check of attributes are the same and
//	            // TODO if email not confirmed send confirmation email.
//	        	
//	            throw new IllegalStateException("username already taken");
//	      }
//	        
//	        String encodedPassword = passwordConvertEncoder().encode(user.getPassword());
////	        String encodedPassword = bCryptPasswordEncoder
////	                .encode(user.getPassword());
//	        
//	        user.setPassword(encodedPassword);
//	        user.setCreateBy("Registered");
//	        user.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
//	        appUserRepository.save(user);
//
//	        token = UUID.randomUUID().toString();
//            
//	        ConfirmationToken confirmationToken = new ConfirmationToken(
//	                token,
//	                LocalDateTime.now(),
//	                LocalDateTime.now().plusMinutes(15),
//	                user
//	        );
//
//	        confirmationTokenService.saveConfirmationToken(
//	                confirmationToken);
//
////	        TODO: SEND EMAIL
//
//	        return token;
//	 }
	 	
	 public int enableAppUser(String email) {
	        return appUserRepository.enableAppUser(email);
	 }
	 
}
