package com.clotheshop.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.clotheshop.dto.ProductDTO;
import com.clotheshop.entities.Category;
import com.clotheshop.entities.Photo;
import com.clotheshop.entities.Product;
import com.clotheshop.repository.CategoryRepository;
import com.clotheshop.repository.PhotoRepository;
import com.clotheshop.repository.ProductRepository;
import com.clotheshop.service.IConvertService;
import com.clotheshop.service.IProductService;

@Service
public class ProductService implements IProductService, IConvertService<ProductDTO, Product> {

	@Autowired
	ProductRepository prodRep;

	@Autowired
	CategoryRepository cateRep;
	
	@Autowired
	PhotoRepository phoRep;

	@Autowired
	ModelMapper mapper;

	@Override
	public List<Product> findAll() {
		return prodRep.findAll();
	}

	@Override
	public List<Product> findPagination(Long id, Integer page, Integer size) {
		Category cate = cateRep.findById(id).get();
		Pageable pages = PageRequest.of(page - 1, size);
		List<Product> products = prodRep.findAllByCategory(cate, pages);
		return products;
	}

	@Override
	public ProductDTO convertToDo(Product product) {
		ProductDTO model = mapper.map(product, ProductDTO.class);
		return model;
	}

	@Override
	public Product convertToEntity(ProductDTO model) {
		Product product = mapper.map(model, Product.class);
		return product;
	}

	@Override
	public Product findOne(Long id) {
		return prodRep.findById(id).get();
	}

	@Override
	public void deleteOne(Long id) {
		prodRep.deleteById(id);
	}

	@Override
	public Product addAndUpdateOne(Product entity) {
		return prodRep.save(entity);
	}

	@Override
	public List<Product> findByCategory(Long idCate) {
		Category cate = cateRep.findById(idCate).get();
		return prodRep.findByCategory(cate);
	}

	@Override
	public List<Product> findHots() {
		return prodRep.find12Records();
	}

	@Override
	public List<Photo> findPhotos(Long id) {
		return phoRep.findByProductId(id);
	}

	@Override
	public int countItemInCart(HttpServletRequest req) {
		List<ProductDTO> notes = (List<ProductDTO>) req.getSession().getAttribute("CART_SESSION");
		if(notes == null) {
			return 0;
		}else {
			return notes.size();
		}
	}

}
