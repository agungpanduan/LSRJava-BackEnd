package com.lsr.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lsr.common.CommonConstants;
import com.lsr.common.CommonMethod;
import com.lsr.constants.Constants;
import com.lsr.request.register.Profile;
import com.lsr.response.CommonResponse;
import com.lsr.service.profile.ProfileService;
import com.lsr.vo.user.UserInfo;
import com.lsr.vo.user.UserVo;

@CrossOrigin
@RestController
@RequestMapping(value = "api/lsr_enhance/main/v1")
public class ProfileController {
	@Autowired
	private Environment env;
	
	@Autowired
	ProfileService profileService;
	
	public ProfileController() {
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("Profile")
	public ResponseEntity<CommonResponse> findAll(@RequestHeader("Authorization") String token//,
			) {//@RequestBody UserRequest userRequest

//		String searchValue = userRequest.getSearchValue().strip();
//		List<UserRequest> listData = userService.searchData(
//				PageRequest.of(userRequest.getPageNumber(), userRequest.getPageSize()), searchValue,
//				userRequest.getStatus());
//
		CommonResponse response = new CommonResponse();
		response.setMessage("tesst");
//
//		response.setStatus("success");
//		response.setMessage("Proccessed Successfully");
//	
//		response.setData(listData);
//		response.setCountData(listData.size());

		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("saveProfil")
	public ResponseEntity<CommonResponse> saveProfil(@RequestBody Profile profile,
			@RequestHeader("Authorization") String token) {
		try {
			String json = (String) CommonMethod.getUserInfo(token, env.getProperty(Constants.JWT_SECRET),
					env.getProperty(Constants.JWT_PREFIX));
			UserInfo userInfo = CommonMethod.jsonToObjectInfo(json);

			if (userInfo.getUsername() != null && !userInfo.getUsername().equals(CommonConstants.STRING_EMPTY)) {
				
				return profileService.saveProfile(profile, userInfo.getUsername())  ;//investigasiService.saveInvestigasi(fraudInvestigasiReq, userInfo.getUserId());
			} else {
				return CommonMethod.badRequest("User Login tidak dapat ditemukan");
			}

		} catch (Exception e) {
			e.printStackTrace();
			CommonResponse response = new CommonResponse();
			response.setStatus("0");
			response.setMessage("ERROR : " + e.getMessage());
			response.setData(null);

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
					.body(response);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("getProfile")
	public ResponseEntity<CommonResponse> getProfil(@RequestParam(required = true) String username,
			@RequestHeader("Authorization") String token) {
		try {
			String json = (String) CommonMethod.getUserInfo(token, env.getProperty(Constants.JWT_SECRET),
					env.getProperty(Constants.JWT_PREFIX));
			UserInfo userInfo = CommonMethod.jsonToObjectInfo(json);
			UserVo profiledata =new UserVo();
			if (userInfo.getUsername() != null && !userInfo.getUsername().equals(CommonConstants.STRING_EMPTY)) {
				profiledata = profileService.getProfileDetails(username, userInfo.getUsername());
				profiledata.setImageData(profileService.downloadImageFromFileSystemString(profiledata.getPath_file()));
				
			} else {
				return CommonMethod.badRequest("User Login tidak dapat ditemukan");
			}

			CommonResponse response = new CommonResponse();
			response.setStatus("1");
			response.setMessage("OK");
			response.setData(profiledata);
			
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
			
		} catch (Exception e) {
			e.printStackTrace();
			CommonResponse response = new CommonResponse();
			response.setStatus("0");
			response.setMessage("ERROR : " + e.getMessage());
			response.setData(null);

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
					.body(response);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("uploadPhoto")
	public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file,
			@RequestHeader("Authorization") String token)  {
		String json = (String) CommonMethod.getUserInfo(token, env.getProperty(Constants.JWT_SECRET),
				env.getProperty(Constants.JWT_PREFIX));
		UserInfo userInfo = CommonMethod.jsonToObjectInfo(json);
		String uploadImage ="";
		if (userInfo.getUsername() != null && !userInfo.getUsername().equals(CommonConstants.STRING_EMPTY)) {
			uploadImage = profileService.uploadImage(file,userInfo.getUsername() );
		} else {
			return CommonMethod.badRequest("User Login tidak dapat ditemukan");
		}
		
		CommonResponse response = new CommonResponse();
		response.setStatus("1");
		response.setMessage("OK");
		response.setData(uploadImage);
		
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
		
	}
	
	
}
