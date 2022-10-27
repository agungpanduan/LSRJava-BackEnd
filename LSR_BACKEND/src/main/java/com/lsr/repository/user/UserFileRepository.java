package com.lsr.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lsr.entity.user.UserFile;

public interface UserFileRepository extends JpaRepository<UserFile,Long> {

	

}
