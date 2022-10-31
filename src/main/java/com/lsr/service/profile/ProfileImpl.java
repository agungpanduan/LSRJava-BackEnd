package com.lsr.service.profile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.lsr.common.CommonConstants;
import com.lsr.common.CommonMethod;
import com.lsr.entity.user.User;
import com.lsr.entity.user.UserFile;
import com.lsr.repository.user.UserFileRepository;
import com.lsr.repository.user.UserRepository;
import com.lsr.request.register.Profile;
import com.lsr.response.CommonResponse;
import com.lsr.response.ValidationResponse;
import com.lsr.util.ImageUtils;
import com.lsr.vo.user.UserVo;

@Service
@Transactional(value = "transactionManager")
public class ProfileImpl implements ProfileService{
	@Autowired
	UserRepository userRepo;
	@Autowired
	UserFileRepository userFile;

	ImageUtils imageutil;
	
	public ProfileImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "unused", "unlikely-arg-type" })
	@Override
	public ResponseEntity<CommonResponse> saveProfile(Profile profile, java.lang.String userLogin) {
		try {
			ValidationResponse validationResponse = new ValidationResponse();
			
			if (profile!=null) {
				validationResponse = validationData(profile);
				if (validationResponse.getValueSuccess() != null) {
					//tidak usah validasi username sudah ada karena ini proses update
//					User user = userRepo.findByUsernameId(userLogin);
//					if (user != null) {
//						return CommonMethod
//								.badRequest("Username already Exists");
//					}
//					else {
//						userRepo.save(user);
//					}
					
					User user = new User();
					user.setUsername(profile.getUsername());
					user.setEmail(profile.getEmail());
					user.setFullname(profile.getFullname());
					user.setGender(profile.getGender());
					Date thedate = new SimpleDateFormat("YYYY-MM-DD", Locale.ENGLISH).parse(profile.getBirthday());
					user.setBirthday(thedate);
					user.setPhone_no(profile.getPhone_no());
					user.setCompany(profile.getCompany());
					user.setPosition(profile.getPosition());
					user.setAddress(profile.getAddress());
					user.setFacebook(profile.getFacebook());
					user.setInstagram(profile.getInstagram());
					user.setTwitter(profile.getTwitter());
					user.setAbout(profile.getAbout());
					Date date= new Date();
				    //getTime() returns current time in milliseconds
					long time = date.getTime();
				    //Passed the milliseconds to constructor of Timestamp class 
					Timestamp ts = new Timestamp(time);
					    
					user.setCreateDate(ts);
					user.setCreateBy(userLogin);
					
					String encodedPassword = passwordConvertEncoder().encode(profile.getPassword());
					user.setPassword(encodedPassword);
					
					userRepo.save(user);
					
					

//					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
//					LocalDateTime now = LocalDateTime.now();  
//					user.setCreateDate(dtf.format(now));
					
//					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
//					Date date = new Date();  
					 
					//Date object
				}
			}
			return null;
		}
		catch (Exception e) {
					e.printStackTrace();
					CommonResponse response = new CommonResponse();
					response.setStatus("0");
					response.setMessage("ERROR : " + e.getMessage());
					response.setData(null);

					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
							.body(response);
		}
	}
	
	private PasswordEncoder passwordConvertEncoder() {
		
	    return new BCryptPasswordEncoder();
	}
	
	public ValidationResponse validationData(Profile profile) {
		ValidationResponse validationResponse = new ValidationResponse();
		String statusProcessErr = CommonConstants.VALUE_ERROR;
		String statusProcessSuc = CommonConstants.VALUE_SUCCESS;
		if (profile.getUsername()==null || profile.getUsername().isEmpty()) {
			validationResponse.setValueError(statusProcessErr);
			validationResponse.setErrMesgs("Username should not be empty");
		}
		else if(profile.getPassword()==null || profile.getPassword().isEmpty()) {
			validationResponse.setValueError(statusProcessErr);
			validationResponse.setErrMesgs("Password should not be empty");
		}
		else if(profile.getEmail()==null || profile.getEmail().isEmpty()) {
			validationResponse.setValueError(statusProcessErr);
			validationResponse.setErrMesgs("Email should not be empty");
		}
		else if(profile.getFullname()==null || profile.getFullname().isEmpty()) {
			validationResponse.setValueError(statusProcessErr);
			validationResponse.setErrMesgs("Fullname should not be empty");
		}
		else {
			//validationResponse.setValueError(statusProcessSuc);
			validationResponse.setValueSuccess(statusProcessSuc);
		}
		
		return validationResponse;
	}

	@Override
	public UserVo getProfileDetails(String username, String userLogin) {
		// TODO Auto-generated method stub
		UserVo userDataVo = new UserVo();
		List<Object[]> dataList = userRepo.getProfile(username);
		
		if (dataList != null && dataList.size() > 0) {
			Object[] obj = (Object[]) dataList.get(0);
		
			userDataVo.setUsername(obj[0] != null ? ((String) obj[0]) : null);
			userDataVo.setFullName(obj[1] != null ? ((String) obj[1]) : null);
			userDataVo.setEmail(obj[2] != null ? ((String) obj[2]) : null);
			userDataVo.setPhone_no(obj[3] != null ? ((String) obj[3]) : null);
			userDataVo.setPosition(obj[4] != null ? ((String) obj[4]) : null);
			userDataVo.setAddress(obj[5] != null ? ((String) obj[5]) : null);
			userDataVo.setPath_file(obj[6] != null ? ((String) obj[6]) : null);
			if (obj[7].toString()=="TRUE") {
				userDataVo.setEnableD(1);
			}
			else {
				userDataVo.setEnableD(0);
			}
			
			if (obj[8].toString()=="TRUE") {
				userDataVo.setLockeD(1);
			}
			else {
				userDataVo.setLockeD(0);
			}
			//userDataVo.setEnabledD(obj[7] != null ? ((bool) obj[7]) : null);
			//userDataVo.setLockedD(obj[8] != null ? ((String) obj[8]) : null);
			userDataVo.setGender(obj[9] != null ? ((String) obj[9]) : null);
			userDataVo.setBirthday(obj[10] != null ? ((Date) obj[10]) : null);
			userDataVo.setFacebook(obj[11] != null ? ((String) obj[11]) : null);
			userDataVo.setTwitter(obj[12] != null ? ((String) obj[12]) : null);
			userDataVo.setInstagram(obj[13] != null ? ((String) obj[13]) : null);
			userDataVo.setAbout(obj[14] != null ? ((String) obj[14]) : null);
			userDataVo.setCompany(obj[15] != null ? ((String) obj[15]) : null);
			
		}
		
		return userDataVo;
	}

	private final String FOLDER_PATH="D:/HASIL PELATIHAN/Java/Projek/UPLOAD/";
	
	@Override
	public String uploadImage(MultipartFile file, String username) {
		 String filePath=FOLDER_PATH+file.getOriginalFilename();
		 
		 String fileName= file.getName();
		 
		 Date date= new Date();
		 //getTime() returns current time in milliseconds
		 long time = date.getTime();
		 //Passed the milliseconds to constructor of Timestamp class 
		 Timestamp ts = new Timestamp(time);
			
//		 UserFile fileData = userFile.save(UserFile.builder()
//				 	.username(username)
//	                .file_name(file.getOriginalFilename())
//	                .file_type(file.getContentType())
//	                .path_file(filePath)
//	                .build()
//				 );
		 
		 
//		 UserFile fileData = new UserFile();
//		 fileData.setUsername(username);
//		 fileData.setFile_name(file.getOriginalFilename());
//		 fileData.setFile_type(file.getContentType());
//		 fileData.setPath_file(filePath);
//		 fileData.setCreateDate(ts);
//		 fileData.setCreateBy(username);;
//		 userFile.save(fileData);
		 
		 UserFile fileData = new UserFile();
		 try {
			 file.transferTo(new File(filePath));
			
			 File input = new File(filePath);
		     BufferedImage image = ImageIO.read(input);
		     
		     BufferedImage resized = imageutil.resize(image, 500, 500);

		     File output = new File(filePath.substring(0, filePath.lastIndexOf(".")) + "_500.png");
		     
		     ImageIO.write(resized, "png", output);
		     input.delete();
		     
			 fileData.setUsername(username);
			 fileData.setFile_name(output.getName());
			 fileData.setFile_type("png");
			 fileData.setPath_file(output.getPath());
			 fileData.setCreateDate(ts);
			 fileData.setCreateBy(username);;
			 userFile.save(fileData);
		     
		 } catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }	

	     if (fileData != null) {
	            return "file uploaded successfully : " + filePath;
	     }
	     
	     return null;
	}

	@Override
	public byte[] downloadImageFromFileSystem(String filePath) {
        byte[] images = null;
		try {
			images = Files.readAllBytes(new File(filePath).toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return images;
	}

	@Override
	public String downloadImageFromFileSystemString(String filePath) {
		byte[] images = null;
		try {
			images = Files.readAllBytes(new File(filePath).toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return  Base64.getEncoder().encodeToString(images);
	}
	
	
}
