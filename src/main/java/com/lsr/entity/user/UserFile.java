package com.lsr.entity.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lsr.entity.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_t_file")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserFile extends BaseEntity{
	
	@Id
	@Column(name = "file_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String file_id;
	
//	@ManyToOne // @ManyToOne beberapa baris bisa di miliki oleh satu user
//	@JoinColumn(name="username")
//	private User username;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "path_file")
	private String path_file;
	
	@Column(name = "file_name")
	private String file_name;
	
	@Column(name = "file_type")
	private String file_type;
	
}
