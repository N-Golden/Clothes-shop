package com.clotheshop.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProductDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;

	private String name;

	private String price;

	private String thumbnail;

	private int category_id;

}
