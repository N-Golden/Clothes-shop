package com.clotheshop.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "categorys")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id // not null and primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increase
	private Long id;

	@Column
	private String name;

	@Column
	private Date createdDate;

	@Column
	private String categoryCode;

	@Column
	private String categoryLink;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Product> products = new ArrayList<>();
	

	public Category(String name) {
		super();
		this.name = name;
	}
	

	
}
