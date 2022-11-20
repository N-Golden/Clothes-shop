package com.clotheshop.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

	@Id // not null and primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increase
	private Long id;

	@Column
	private String fullname;

	@Column
	private String email;

	@Column
	private String password;

	@Column
	private Date createdDate;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;
}
