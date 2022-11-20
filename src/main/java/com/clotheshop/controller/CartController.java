package com.clotheshop.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.clotheshop.dto.ProductDTO;
import com.clotheshop.entities.Product;
import com.clotheshop.service.IConvertService;
import com.clotheshop.service.IProductService;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private IProductService service;

	@Autowired
	private IConvertService<ProductDTO, Product> ser1;

	@GetMapping("")
	public String cart(Model model, HttpSession session) {
		List<ProductDTO> notes = (List<ProductDTO>) session.getAttribute("CART_SESSION");
		model.addAttribute("cart", notes);
		return "web/cart";
	}

	@GetMapping("/add")
	public String addCart(Model model, @RequestParam(required = false) Long id, HttpServletRequest req) {
		Product p = service.findOne(id);
		ProductDTO pt = ser1.convertToDo(p);
		List<ProductDTO> notes = (List<ProductDTO>) req.getSession().getAttribute("CART_SESSION");
		if (notes == null) {
			notes = new ArrayList<>();
//	             if notes object is not present in session, set notes in the request session
			req.getSession().setAttribute("CART_SESSION", notes);
		}
		notes.add(pt);
		req.getSession().setAttribute("CART_SESSION", notes);
		return "redirect:/cart";
	}

	@GetMapping("/delete")
	public String deleteCart(Model model, @RequestParam Long id, HttpServletRequest req) {
		List<ProductDTO> notes = (List<ProductDTO>) req.getSession().getAttribute("CART_SESSION");
		Iterator<ProductDTO> it = notes.iterator();
		while (it.hasNext()) {
			ProductDTO product = it.next();
			if (product.getId() == id) {
				it.remove();
			}
		}
		req.getSession().setAttribute("CART_SESSION", notes);
		return "redirect:/cart";
	}
}
