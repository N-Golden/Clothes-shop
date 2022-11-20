package com.clotheshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.clotheshop.service.ICategoryService;
import com.clotheshop.service.IProductService;

@Controller
@RequestMapping("/")
public class ClientController {

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private IProductService productService;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("categorys", categoryService.findAll());
		return "web/index";
	}

	@GetMapping("/about")
	public String about() {
		return "web/about";
	}

	@GetMapping("/shop")
	public String shop(Model model, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "6") Integer size, @RequestParam(defaultValue = "1") Long id) {

		model.addAttribute("allProducts", productService.findByCategory(id));
		model.addAttribute("categorys", categoryService.findAll());
		model.addAttribute("products", productService.findPagination(id, page, size));
		model.addAttribute("currentPage", page);
		model.addAttribute("idCate", id);

		return "web/shop";
	}

	@GetMapping("/contact")
	public String contact() {
		return "web/contact";
	}

	@GetMapping("/login")
	public String login() {
		return "web/login";
	}

}
