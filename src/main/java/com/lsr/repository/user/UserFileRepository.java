package com.lsr.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lsr.entity.user.UserFile;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFileRepository extends JpaRepository<UserFile,Long> {

	

}
