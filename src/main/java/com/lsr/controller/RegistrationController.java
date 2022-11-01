package com.lsr.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lsr.request.register.RegistrationRequest;
import com.lsr.response.RegiterResponse;
import com.lsr.service.register.RegistrationService;

@CrossOrigin
@RestController
@RequestMapping(value = "api/lsr_enhance/main/v1")
public class RegistrationController {
	private static final String REDIRECT_LOGIN= "http://localhost:4200/login";
	
	 @Autowired 
	 private RegistrationService registrationService;
	 
	 @SuppressWarnings({ "rawtypes" })
	 @PostMapping("register")
	 private ResponseEntity<RegiterResponse> register(@RequestBody RegistrationRequest request) {
		RegiterResponse responseApi = new RegiterResponse();
	   	try { 
	   			
	   		responseApi = registrationService.register(request);
	   	   	if (responseApi.getToken()!=null && responseApi.getToken() !="") {
	   	   		responseApi.setToken(responseApi.getToken());
	   	   		responseApi.setMessage("Your account has been made, please verify it by clicking the activation link that has been send to your email.");
	   	   		responseApi.setUsername(request.getUsername());
	   	   		responseApi.setEmail(request.getEmail());	
	   	   		responseApi.setStatus("success");
	   	   	}
	   	   	else {
	   	   			
	   	   	}
	   	   		
		  } catch(Exception e) {
			  responseApi.setStatus("Error");
			  responseApi.setMessage(e.getMessage()); 
		  }
	   		
	   	if (responseApi.getToken()==null) {
			 responseApi.setStatus("Error");  
		 }
	   		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(responseApi);
	 }
	 
	 @GetMapping("confirm")
	 @SuppressWarnings({ "rawtypes" }) //HttpServletRequest request, 
	 private void confirm(@RequestParam("token") String token, @RequestParam("username") String username,RedirectAttributes redirAttr, HttpServletResponse response) throws IOException {
		 RegiterResponse responseApi = new RegiterResponse();
		 
		 try {
			 responseApi=registrationService.confirmToken(token,username);
			 
			 responseApi.setStatus("success");
			 //return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(responseApi);
			 
		 }
		 catch(Exception e) {
			 responseApi.setStatus("Error");
			 responseApi.setMessage(e.getMessage());
			 //return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(responseApi);
			 
		 }
		   
		 if (responseApi.getToken()==null) {
			 responseApi.setStatus("Error");  
		 }
		 
		 redirAttr.addFlashAttribute("Status", responseApi.getStatus()); 
		 redirAttr.addFlashAttribute("Message", responseApi.getMessage()); 
		 redirAttr.addFlashAttribute("Username", responseApi.getUsername()); 
		
//		 response.addHeader("Access-Control-Allow-Origin", "*");
//		 response.setContentType("application/json");
//		 response.setCharacterEncoding("UTF-8");
//		 response.getWriter().print(responseApi);
//		 response.getWriter().flush();
		 response.sendRedirect(REDIRECT_LOGIN + "?username="+username);  
		 //return REDIRECT_LOGIN;
		 
	 }
	 
//	 @PostMapping("register")
//	 public String register(@RequestBody RegistrationRequest request) {
//		    registrationService.register(request);
//	        return registrationService.register(request);
//	 }

//	 @GetMapping("confirm")
//	 //@RequestMapping(value = "http://localhost:4200/Login")
//	 @SuppressWarnings({ "rawtypes" })
//	 private ResponseEntity<RegiterResponse> confirm(@RequestParam("token") String token, @RequestParam("username") String username,RedirectAttributes redirAttr) {
//		 RegiterResponse responseApi = new RegiterResponse();
//		 
//		 try {
//			 responseApi=registrationService.confirmToken(token,username);
//			 
//			 responseApi.setStatus("success");
//			 //return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(responseApi);
//			 
//		 }
//		 catch(Exception e) {
//			 responseApi.setStatus("Error");
//			 responseApi.setMessage(e.getMessage());
//			 //return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(responseApi);
//			 
//		 }
//		   
//		 if (responseApi.getToken()==null) {
//			 responseApi.setStatus("Error");  
//		 }
//		 
//		 redirAttr.addFlashAttribute("Status", responseApi.getStatus()); 
//		 redirAttr.addFlashAttribute("Message", responseApi.getMessage()); 
//		 redirAttr.addFlashAttribute("Usedname", responseApi.getUsername()); 
//		 return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(responseApi);
//	 }
	 
	

}
