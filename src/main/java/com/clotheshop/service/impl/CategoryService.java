package com.clotheshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clotheshop.entities.Category;
import com.clotheshop.repository.CategoryRepository;
import com.clotheshop.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService {

	@Autowired
	private CategoryRepository results;

	@Override
	public List<Category> findAll() {
		return results.find3Records();
	}

}
