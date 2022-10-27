package com.lsr.entity.register.token;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lsr.entity.user.*;
import com.lsr.service.user.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "ConfirmationToken")
public class ConfirmationToken {

		@Id
	    @Column(nullable = false,name="token")
	    private String token;

	    @Column(nullable = false,name="createdAt")
	    private Timestamp createdAt;

	    @Column(nullable = false,name="expiresAt")
	    private Timestamp expiresAt;

	    private Timestamp confirmedAt;
	    
	    @ManyToOne
	    @JoinColumn(
	            nullable = false,
	            name = "username"
	    )
	    private User appUser;
	    
	    public ConfirmationToken(String token,
	                             LocalDateTime createdAt,
	                             LocalDateTime expiresAt,
	                             User user ) {
	        this.token = token;
	        this.createdAt = Timestamp.valueOf(createdAt);
	        this.expiresAt = Timestamp.valueOf(expiresAt);
	        this.appUser = user;
	    }
}
