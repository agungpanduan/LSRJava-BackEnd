package com.lsr.entity.register.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lsr.entity.register.token.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ConfirmationTokenRepository
        extends JpaRepository<ConfirmationToken, Long> {

    Optional<ConfirmationToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query(value ="UPDATE ConfirmationToken " +
            "SET confirmedAt =:confirmedAt " +
            "WHERE username =:username and token =:token", nativeQuery = true)
    int updateConfirmedAt(@Param("token") String token,
    							  @Param("confirmedAt") Timestamp confirmedAt,
    							  @Param("username") String username);
    
    
}
