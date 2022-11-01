package com.lsr.entity.user;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lsr.entity.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@IdClass(userroleid.class)
@Table(name = "TB_M_USER_ROLE")
public class UserRole extends BaseEntity{
	@Id
    @Column(name = "username")
    private String username;

    @Id
    @Column(name = "role_cd")
    private String role;
	
    public UserRole() {
    }

    public UserRole(String username, String role) {
        this.username = username;
        this.role = role;
    }
}
