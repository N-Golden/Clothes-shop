package com.clotheshop.entities;

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
@Table(name = "photos")
@Data
public class Photo {
	@Id // not null and primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increase
	private Long id;
	
	@Column(name="path_Location")
	private String pathLocation;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
}
