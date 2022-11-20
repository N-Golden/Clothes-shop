package com.clotheshop.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProductDTO implements Serializable {

	private long id;

	private String name;

	private String shortDescription;

	private double price;

	private int numStart;

	private String image;

	private int category_id;

}
