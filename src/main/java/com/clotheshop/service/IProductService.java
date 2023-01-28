package com.clotheshop.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.clotheshop.entities.Photo;
import com.clotheshop.entities.Product;

public interface IProductService {
	List<Product> findAll();

	List<Product> findPagination(Long id, Integer page, Integer size);

	Product findOne(Long id);

	void deleteOne(Long id);

	Product addAndUpdateOne(Product entity);

	List<Product> findByCategory(Long idCate);
	
	List<Product> findHots();
	
	List<Photo> findPhotos(Long id);
	
	int countItemInCart(HttpServletRequest req);

}
