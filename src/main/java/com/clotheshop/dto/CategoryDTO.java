package com.clotheshop.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CategoryDTO {
	private Long id;

	private String name;

	private String categoryLink;

	private Date createdDate;

	private String categoryCode;
}
