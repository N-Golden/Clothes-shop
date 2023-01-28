package com.clotheshop.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
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
	public ResponseEntity<String> deleteProduct(@RequestBody long[] arrId) {
		try {
			for (long id : arrId) {
				service.deleteOne(id);
			}
			HttpHeaders httpHeaders = new HttpHeaders();
			return new ResponseEntity<>("Product is deleted successfully", httpHeaders, HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>("Data is not found", HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/api/product/post")
	public ResponseEntity<Product> addProduct(@RequestPart MultipartFile file, String name, String price) {
		String fileName = saveFileUtils.saveFile(file);
		String newPrice = price.subSequence(0, price.length()-3) +"."+ price.substring(price.length()-3, price.length());
		Product entity = new Product();
		entity.setThumbnail(fileName);
		entity.setName(name);
		entity.setPrice(newPrice);
		service.addAndUpdateOne(entity);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

	@PostMapping("/api/product/update")
	public ResponseEntity<Product> updateProduct(@RequestPart(required = false) MultipartFile file, String name, String price, Long id) {
		String fileName = saveFileUtils.saveFile(file);
		
		Product entity = service.findOne(id);
		entity.setThumbnail(fileName);
		entity.setName(name);
		entity.setPrice(price);
		service.addAndUpdateOne(entity);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

}
