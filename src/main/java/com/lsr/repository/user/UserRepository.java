package com.lsr.repository.user;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.lsr.entity.user.User;
import com.lsr.vo.user.UserVo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	@Query(value = " select tmur.username, tmur.role_cd roleCd,tmr.role_name roleName,us.fullname fullName,us.email, tmrmf.menu_cd menuCd, tmrmf.feature_cd featureCd, tmm.menu_name menuName,tmm.menu_desc menuDesc, tmm.Icon iCon, tmm.\"Action\" as acTion, enabled enableD, \"locked\" as lockeD from tb_m_role_menu_feat tmrmf "
				 + " inner join tb_m_user_role tmur on tmrmf.role_cd = tmur.role_cd"
				 + " inner join tb_m_user us on us.username = tmur.username"
				 + " inner join tb_m_role tmr ON tmr.role_cd =tmur.role_cd  and tmrmf.role_cd = tmr.role_cd"
				 + " inner join tb_m_menu tmm on tmrmf.menu_cd =tmm.menu_cd "
				 + " where us.enabled = true and tmur.username =:username "
			 + "  ORDER BY tmm.seq, tmm.menu_seq ASC ", nativeQuery = true)
	List<Object[]> getDataUserMenuRepo(@Param("username") String username);

	@Query(value = "select * from tb_m_user where username=:username", nativeQuery = true)
	Optional<User> findByUsername(@Param("username") String username);
	
	@Query(value = "select username, password,email, enabled enableD, \"locked\" as lockeD from tb_m_user where email=:email", nativeQuery = true)
	Optional<User> findByEmail(@Param("email") String email);
	
	@Query(value = "select * from tb_m_user where username=:username", nativeQuery = true)
	User findByUsernameId(@Param("username") String username);
	
	@Transactional
	@Modifying
	@Query(value ="update tb_m_user "
			+ "set enabled = true "
			+ "where email =:email", nativeQuery = true)
	int enableAppUser(@Param("email") String email);
	
	@Query(value = "select A.username,fullname,email,phone_no,\"position\",address,B.path_file,enabled,\"locked\",gender,birthday,facebook,twitter,instagram,about,company from tb_m_user A\r\n"
			+ "inner join tb_t_file B on A.username = B.username \r\n"
			+ "where A.username=:username\r\n"
			+ "limit 1", nativeQuery = true)
	List<Object[]> getProfile(@Param("username") String username);
	
	
}
