package com.clotheshop.entities;

import java.io.Serializable;
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
@Table(name = "products")
@Data
public class Product implements Serializable {

	@Id // not null and primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increase
	private Long id;

	@Column
	private String name;

	@Column
	private Date createdDate;

	@Column
	private Double price;

	@Column
	private Double discount;

	@Column
	private String image;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

}
