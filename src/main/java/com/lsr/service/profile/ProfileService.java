package com.lsr.service.profile;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.lsr.response.*;
import com.lsr.vo.user.UserVo;
import com.lsr.entity.user.User;
import com.lsr.request.register.*;
import com.lsr.request.user.UserRequest;
	
public interface ProfileService {

	@SuppressWarnings("rawtypes")
	public ResponseEntity<CommonResponse> saveProfile(Profile profile, String userLogin);	
	
	public UserVo getProfileDetails (String username, String userLogin);
	
	public String uploadImage(MultipartFile file, String username);
	
	public byte[] downloadImageFromFileSystem(String filePath);
	
	public String downloadImageFromFileSystemString(String filePath);
}
