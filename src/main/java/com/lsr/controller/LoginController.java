package com.lsr.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import com.lsr.service.user.*;
import com.lsr.common.CommonConstants;
import com.lsr.common.CommonMethod;
import com.lsr.constants.Constants;
import com.lsr.request.RefreshTokenRequest;
import com.lsr.request.register.RegistrationRequest;
import com.lsr.response.*;
import com.lsr.vo.user.*;

@CrossOrigin
@RestController
@RequestMapping(value = "api/lsr_enhance/main/v1")
public class LoginController {
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private Environment env;

	@Autowired
	private UserService userService;

	@Autowired
	private String jwtRefreshSecret;

	@Autowired
	private String jwtPrefix;

	@Autowired
	private String jwtExpired;

	@Autowired
	private String jwtRefreshExpired;
	
	@SuppressWarnings({ "rawtypes" })
	@PostMapping("login")
	public ResponseEntity<LoginResponse> authenticate(@RequestBody @Validated User user) throws Exception {
		
		if (user.getusername().isEmpty()) {
			LoginResponse response = new LoginResponse();
			response.setStatus("0");
			response.setMessage("Login harus diisi  ");

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(response);
		}
		if (user.getPassword().isEmpty()) {
			LoginResponse response = new LoginResponse();
			response.setStatus("0");
			response.setMessage("Password harus diisi  ");

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(response);
		}

		return getByPassLogin(user, false,true);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ResponseEntity<LoginResponse> getByPassLogin(User user, Boolean ssoLogin, Boolean LoginPass) {
		if (!ssoLogin && LoginPass) {
			
//			Authentication authentication = authenticationManager.authenticate(
//			       new UsernamePasswordAuthenticationToken(user.getusername(), user.getPassword()));
			Authentication authentication;
			LoginResponse response = new LoginResponse();
			 try { 
				 authentication = authenticationManager.authenticate( new
					 UsernamePasswordAuthenticationToken(user.getusername(), user.getPassword()));
			 } catch (AccountStatusException ase) { //covers expired, locked, disabled
				response.setStatus("0");
				response.setMessage("Invalid user credentials");
//				return ResponseEntity.badRequest().body(response);
				 return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
							.body(response);
			 } 
			 catch (BadCredentialsException e){
				response.setStatus("0");
				response.setMessage("Invalid user credentials");
//				return ResponseEntity.badRequest().body(response);
				 return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
							.body(response);
			 }
			
			 
			
			if (authentication.isAuthenticated()) {
				LoginResponse responseApi = new LoginResponse();
				
				UserVo dataUserVo = new UserVo();
				List<UserAccessVo> userMenuAccessList = userService.getDataMenuAccess(user.getusername().toLowerCase().trim());
				
				if (userMenuAccessList != null && userMenuAccessList.size() > 0) {
					dataUserVo.setUserAccessList(userMenuAccessList);
				} else {
					userMenuAccessList = userService.getDataMenuAccessDefault();
					dataUserVo.setUserAccessList(userMenuAccessList);
				}
				
				dataUserVo.setUsername(user.getusername());
				dataUserVo.setEmail(userMenuAccessList.get(0).getEmail());
				dataUserVo.setRoleCd(userMenuAccessList.get(0).getRoleCd());
				dataUserVo.setRoleName(userMenuAccessList.get(0).getRoleName());
				dataUserVo.setFullName(userMenuAccessList.get(0).getFullName());
				 
				responseApi.setEmail(userMenuAccessList.get(0).getEmail());
				responseApi.setStatus("1");
				responseApi.setMessage("Login Success");
				responseApi.setData(dataUserVo);
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

				String token = getJWTTokenLogin(user.getusername(), dataUserVo.getEmail(), null);
				responseApi.setToken(token.replace("Bearer ", ""));
				responseApi.setTokenExpDate(sdf.format(
						new Date(System.currentTimeMillis() + Integer.parseInt(env.getProperty(Constants.JWT_EXPIRED)))));
				responseApi.setTokenAge(env.getProperty(Constants.JWT_EXPIRED));

				String refreshToken = getJWTRefreshTokenLogin(user.getusername(),dataUserVo.getEmail(), null);
				responseApi.setRefreshToken(refreshToken.replace("Bearer ", ""));
				responseApi.setRefreshTokenExpDate(sdf.format(new Date(
						System.currentTimeMillis() + Integer.parseInt(env.getProperty(Constants.JWT_REFRESH_EXPIRED)))));
				responseApi.setRefreshTokenAge(env.getProperty(Constants.JWT_REFRESH_EXPIRED));

				return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(responseApi);
			
			
			} else {
				//LoginResponse response = new LoginResponse();
				response.setStatus("0");
				response.setMessage("Invalid user credentials");

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
					.body(response);
			}
		}
		else if (!ssoLogin) {
			//INI DIGUNAKAN UNTUK GET DATA LOGIN DARI SSO BTPN 
			//ADA TABLE USER PASSWORD DI SSO LOGIN BTPN
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE.toString());
			headers.add("Accept", MediaType.APPLICATION_JSON.toString()); // Optional in case server sends back JSON

			MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<String, String>();
			requestBody.add("client_id", "lsr_enhance");
			requestBody.add("grant_type", "password");
			requestBody.add("username", user.getusername());
			requestBody.add("password", user.getPassword());
			requestBody.add("client_secret", "c8b0d364-9c19-4418-87bf-21ed249a5db1");
			
//			requestBody.add("client_secret", "0290758f-7820-4fb3-aca0-0e6c59fc67c0");
			requestBody.add("device", "5cawGeBVHABP/GLKfxcpBhU2Tak");

			HttpEntity formEntity = new HttpEntity<MultiValueMap<String, String>>(requestBody, headers);
			RestTemplate restTemplate = new RestTemplate();
			
			ResponseEntity<UserLdapVo> ldapLogin = restTemplate.postForEntity(CommonConstants.URL_LDAP, formEntity,
					UserLdapVo.class);

			if (ldapLogin != null && ldapLogin.getBody() != null) {
				if (ldapLogin.getBody().getAccess_token() != null
						&& !ldapLogin.getBody().getAccess_token().equals(CommonConstants.STRING_EMPTY)) {
					user.setusername(user.getusername().toLowerCase().trim());
					LoginResponse responseApi = new LoginResponse();

					UserVo dataUserVo = new UserVo();
					List<UserAccessVo> userMenuAccessList = userService
							.getDataMenuAccess(user.getusername().toLowerCase().trim());
					if (userMenuAccessList != null && userMenuAccessList.size() > 0) {
						dataUserVo.setUserAccessList(userMenuAccessList);
					} else {
						userMenuAccessList = userService.getDataMenuAccessDefault();
						dataUserVo.setUserAccessList(userMenuAccessList);
					}

					DecodedJWT jwt = JWT.decode(ldapLogin.getBody().getId_token());
					Map<String, Object> mapClaim = jwt.getClaims().get("profile").asMap();
					dataUserVo.setEmail(mapClaim.get("email").toString() + "@gmail.com");
					dataUserVo.setFullName(jwt.getClaims().get("fullname").asString());

					responseApi.setStatus("1");
					responseApi.setMessage("Login Success");
					responseApi.setData(dataUserVo);

					SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
					
					String token = getJWTTokenLogin(user.getusername(), dataUserVo.getEmail(),
							null);
					responseApi.setToken(token.replace("Bearer ", ""));
					responseApi.setTokenExpDate(sdf.format(new Date(
							System.currentTimeMillis() + Integer.parseInt(env.getProperty(Constants.JWT_EXPIRED)))));
					responseApi.setTokenAge(env.getProperty(Constants.JWT_EXPIRED));
											
					String refreshToken = getJWTRefreshTokenLogin(user.getusername().toString(),
							dataUserVo.getEmail(), null);
					responseApi.setRefreshToken(refreshToken.replace("Bearer ", ""));
					responseApi.setRefreshTokenExpDate(sdf.format(new Date(System.currentTimeMillis()
							+ Integer.parseInt(env.getProperty(Constants.JWT_REFRESH_EXPIRED)))));
					responseApi.setRefreshTokenAge(env.getProperty(Constants.JWT_REFRESH_EXPIRED));

					return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
							.body(responseApi);
				} else {
					LoginResponse response = new LoginResponse();
					response.setStatus("0");
					response.setMessage("Invalid user credentials");

					return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
							.body(response);
				}
			} else {
				LoginResponse response = new LoginResponse();
				response.setStatus("0");
				response.setMessage("Invalid user credentials");

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
						.body(response);
			}
		} else {

			LoginResponse responseApi = new LoginResponse();
			
			UserVo dataUserVo = new UserVo();
			List<UserAccessVo> userMenuAccessList = userService.getDataMenuAccess(user.getusername().toLowerCase().trim());
			
			if (userMenuAccessList != null && userMenuAccessList.size() > 0) {
				dataUserVo.setUserAccessList(userMenuAccessList);
			} else {
				userMenuAccessList = userService.getDataMenuAccessDefault();
				dataUserVo.setUserAccessList(userMenuAccessList);
			}
			
			dataUserVo.setUsername(user.getusername());
			dataUserVo.setEmail(userMenuAccessList.get(0).getEmail());
			dataUserVo.setRoleCd(userMenuAccessList.get(0).getRoleCd());
			dataUserVo.setRoleName(userMenuAccessList.get(0).getRoleName());
			dataUserVo.setFullName(userMenuAccessList.get(0).getFullName());
			 
			responseApi.setEmail(userMenuAccessList.get(0).getEmail());
			responseApi.setStatus("1");
			responseApi.setMessage("Login Success");
			responseApi.setData(dataUserVo);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

			String token = getJWTTokenLogin(user.getusername(), dataUserVo.getEmail(), null);
			responseApi.setToken(token.replace("Bearer ", ""));
			responseApi.setTokenExpDate(sdf.format(
					new Date(System.currentTimeMillis() + Integer.parseInt(env.getProperty(Constants.JWT_EXPIRED)))));
			responseApi.setTokenAge(env.getProperty(Constants.JWT_EXPIRED));

			String refreshToken = getJWTRefreshTokenLogin(user.getusername(),dataUserVo.getEmail(), null);
			responseApi.setRefreshToken(refreshToken.replace("Bearer ", ""));
			responseApi.setRefreshTokenExpDate(sdf.format(new Date(
					System.currentTimeMillis() + Integer.parseInt(env.getProperty(Constants.JWT_REFRESH_EXPIRED)))));
			responseApi.setRefreshTokenAge(env.getProperty(Constants.JWT_REFRESH_EXPIRED));

			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(responseApi);
		}
	}

	private String getJWTTokenLogin(String userName, String email, Integer expTime) {
		if (expTime == null) {
			expTime = Integer.parseInt(env.getProperty(Constants.JWT_EXPIRED));
		}
		String secretKey = env.getProperty(Constants.JWT_SECRET);
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
		UserInfo ui = new UserInfo(userName, email);

		String token = Jwts.builder().setId(UUID.randomUUID().toString()).setSubject(CommonMethod.objectToJson(ui))
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + (expTime)))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

		return env.getProperty(Constants.JWT_PREFIX) + token;
	}

	private String getJWTRefreshTokenLogin(String userName, String email, Integer expTime) {
		if (expTime == null) {
			expTime = Integer.parseInt(env.getProperty(Constants.JWT_REFRESH_EXPIRED));
		}
		String secretKey = env.getProperty(Constants.JWT_REFRESH_SECRET);
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
		UserInfo ui = new UserInfo(userName, email);

		String token = Jwts.builder().setId(UUID.randomUUID().toString()).setSubject(CommonMethod.objectToJson(ui))
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + (expTime)))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

		return env.getProperty(Constants.JWT_PREFIX) + token;
	}
	
//	@SuppressWarnings("rawtypes")
//	@PostMapping("refreshToken")
//	private ResponseEntity<LoginResponse> refreshToken(@RequestBody RefreshTokenRequest data)  throws Exception{
//		System.out.println("testing username "); 
//		LoginResponse model = new LoginResponse();
//		//Map<Object, Object> model = new HashMap<>();
//		data.setUsername(data.getUsername().toLowerCase().trim());
//		String username = data.getUsername().toLowerCase().trim();
//		String refreshToken = data.getRefreshToken();
//
//		Object json = CommonMethod.getUserInfo(refreshToken, jwtRefreshSecret, jwtPrefix);
//		if (!StringUtils.isEmpty(json)) {
//			UserInfo userInfo = CommonMethod.jsonToObjectInfo(json.toString());
//			if (userInfo != null && userInfo.getUsername() != null) {
//				if (username.equals(userInfo.getUsername().toLowerCase().trim())) {
//					String token = getJWTTokenLogin(userInfo.getUsername(), userInfo.getEmail(),
//							null);
//					SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
//					model.setToken(token);
//					model.setTokenExpDate(sdf.format(new Date(System.currentTimeMillis() + Integer.parseInt(jwtExpired))));
//					model.setTokenAge(jwtExpired);
//					String refreshTokenNew = getJWTRefreshTokenLogin(userInfo.getUsername(),
//							userInfo.getEmail(), null);
//					model.setRefreshToken(refreshTokenNew);
//					model.setRefreshTokenExpDate(sdf.format(new Date(System.currentTimeMillis() + Integer.parseInt(jwtRefreshExpired))));
//					model.setRefreshTokenAge(jwtRefreshExpired);
//					
//					return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(model);
//				}
//			}
//		}
//
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
//				.body(model);
//
//	}

	@PostMapping("refreshToken")
	public ResponseEntity<Object> refreshToken(@RequestBody RefreshTokenRequest data)  throws Exception{
		System.out.println("testing username "); 
		Map<Object, Object> model = new HashMap<>();
		data.setUsername(data.getUsername().toLowerCase().trim());
		// get username
		// for now, pretend it is s
		String username = data.getUsername().toLowerCase().trim();
		String refreshToken = data.getRefreshToken();

		Object json = CommonMethod.getUserInfo(refreshToken, jwtRefreshSecret, jwtPrefix);
		if (!StringUtils.isEmpty(json)) {
			UserInfo userInfo = CommonMethod.jsonToObjectInfo(json.toString());
			if (userInfo != null && userInfo.getUsername() != null) {
				if (username.equals(userInfo.getUsername().toLowerCase().trim())) {
					String token = getJWTTokenLogin(userInfo.getUsername(), userInfo.getEmail(),
							null);
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
					model.put("token", token);
					model.put("tokenExpDate",
							sdf.format(new Date(System.currentTimeMillis() + Integer.parseInt(jwtExpired))));
					model.put("tokenAge", jwtExpired);

					String refreshTokenNew = getJWTRefreshTokenLogin(userInfo.getUsername(),
							userInfo.getEmail(), null);
					model.put("refreshToken", refreshTokenNew.replace("Bearer ", ""));
					model.put("refreshTokenExpDate",
							sdf.format(new Date(System.currentTimeMillis() + Integer.parseInt(jwtRefreshExpired))));
					model.put("refreshTokenAge", jwtRefreshExpired);

					return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(model);
				}
			}
		}

		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

	}

	
}
