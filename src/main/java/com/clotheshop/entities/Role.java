package com.clotheshop.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class Role {

	@Id // not null and primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increase
	private Long id;

	@Column
	private String name;

	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
	private List<User> users = new ArrayList<>();
}
