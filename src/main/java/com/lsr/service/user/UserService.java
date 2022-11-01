package com.lsr.service.user;
import java.util.List;

import org.springframework.data.domain.Pageable;
import com.lsr.request.user.*;
import com.lsr.vo.user.UserAccessVo;
import com.lsr.entity.user.*;

public interface UserService {
	public List<UserRequest> searchData(Pageable pageable, String searchValue, String status);
	
	public List<User> create (UserRequest req, String userLogin);
	
	public void delete (String nik, String userLogin);
	
	public User findById (Long username);
	
	public UserRequest getById (Long username);
	
	public List<User> update (UserRequest req, String userLogin);
	
	public List<UserAccessVo> getDataMenuAccess(String username);	
	
	public List<UserAccessVo> getDataMenuAccessDefault();
	
	public List<UserAccessVo> getDataMenuFeatAccess(String role_cd);
}
