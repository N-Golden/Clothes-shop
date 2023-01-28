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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product implements Serializable {

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
	private String price;

	@Column
	private String thumbnail;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Photo> photos = new ArrayList<>();
	
	
	public Product(Long id) {
		this.id = id ;
	}
	
	public Product( String name, String thumbnail, String price) {
		this.name = name;
		this.thumbnail = thumbnail;
		this.price = price;
	}

	public Product(Long id, String name, String price, String thumbnail) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.thumbnail = thumbnail;
	}
}
