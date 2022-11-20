package com.clotheshop.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.clotheshop.entities.Product;
import com.clotheshop.service.IProductService;
import com.clotheshop.utils.saveFileUtils;

@RestController()
public class productAPI {

	@Autowired
	private IProductService service;

//	@Autowired
//	private IConvertService<ProductDTO, Product> convert;
//
//	@GetMapping("/api/product")
//	public List<ProductDTO> getProduct(@RequestBody PageDTO model) {
//		List<Product> prodEnti = service.findPagination(model.getPage(), model.getSize());
//		List<ProductDTO> products = new ArrayList<>();
//		for (Product entity : prodEnti) {
//			products.add(convert.convertToDo(entity));
//		}
//		return products;
//	}

	@DeleteMapping("/api/product")
	public boolean deleteProduct(@RequestBody long[] arrId) {
		for (long id : arrId) {
			service.deleteOne(id);
			return true;
		}
		return false;
	}

	@PostMapping("/api/product")
	public void addProduct(@RequestParam MultipartFile file, String name, Double price) {
		String fileName = saveFileUtils.saveFile(file);
		Product entity = new Product();
		entity.setImage(fileName);
		entity.setName(name);
		entity.setPrice(price);
		service.addAndUpdateOne(entity);
	}

	@PutMapping("/api/product")
	public void updateProduct(@RequestParam(required = false) MultipartFile file, String name, Double price, Long id) {
		String fileName = saveFileUtils.saveFile(file);
		Product entity = service.findOne(id);
		entity.setImage(fileName);
		entity.setName(name);
		entity.setPrice(price);
		service.addAndUpdateOne(entity);
	}

}
