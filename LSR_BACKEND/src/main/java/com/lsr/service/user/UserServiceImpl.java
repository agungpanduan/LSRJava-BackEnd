package com.lsr.service.user;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lsr.common.CommonConstants;
import com.lsr.entity.user.User;
import com.lsr.repository.user.*;
import com.lsr.request.user.UserRequest;
import com.lsr.vo.user.UserAccessVo;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	//@Autowired
	//RoleRepository roleRepo;

	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<UserRequest> searchData(Pageable pageable, String searchValue, String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> create(UserRequest req, String userLogin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String nik, String userLogin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User findById(Long username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRequest getById(Long username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> update(UserRequest req, String userLogin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserAccessVo> getDataMenuAccess(String username) {
		List<UserAccessVo> userList = new ArrayList<>();
		try {
			List<Object[]> objDataList = userRepository.getDataUserMenuRepo(username);
			if (objDataList != null && objDataList.size() > 0) {
				for (int i = 0; i < objDataList.size(); i++) {
					Object[] obj = (Object[]) objDataList.get(i);

					UserAccessVo userTemp = new UserAccessVo();
					//sesuaikan dengan urutan kolom di Query 
					//tmur.username, tmur.role_cd,tmr.role_name,us.fullname,us.email, tmrmf.menu_cd, tmrmf.feature_cd, tmm.menu_name,tmm.menu_desc
					
					userTemp.setUsername(obj[0] != null ? (String) obj[0] : null);
					userTemp.setRoleCd (obj[1] != null ? ((BigInteger) obj[1]).longValue() : null);
					userTemp.setRoleName(obj[2] != null ? (String) obj[2] : null);
					userTemp.setFullName(obj[3] != null ? (String) obj[3] : null);
					userTemp.setEmail(obj[4] != null ? (String) obj[4] : null);
					userTemp.setMenuCd(obj[5] != null ? (String) obj[5] : null);
					userTemp.setFeatureCd(obj[6] != null ? (String) obj[6] : null);
					userTemp.setMenuName(obj[7] != null ? (String) obj[7] : null);
					userTemp.setMenuDesc(obj[8] != null ? (String) obj[8] : null);
					userTemp.setICon(obj[9] != null ? (String) obj[9] : null);
					userTemp.setAcTion(obj[10] != null ? (String) obj[10] : null);
				
					userList.add(userTemp);
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return userList;
	}

	@Override
	public List<UserAccessVo> getDataMenuAccessDefault() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserAccessVo> getDataMenuFeatAccess(String role_cd) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
