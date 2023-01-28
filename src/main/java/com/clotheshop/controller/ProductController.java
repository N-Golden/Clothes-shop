package com.clotheshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.clotheshop.service.IProductService;

@Controller
@RequestMapping("/item")
public class ProductController {
	
	@Autowired
	private IProductService prodSer;
	
	@GetMapping("/details")
	public String itemDetail(@RequestParam(defaultValue = "41") Long id, Model model) {
		model.addAttribute("product",prodSer.findOne(id));
		model.addAttribute("photos", prodSer.findPhotos(id));
		model.addAttribute("relevents", prodSer.findHots());
		return "web/item-details";
	}

}
