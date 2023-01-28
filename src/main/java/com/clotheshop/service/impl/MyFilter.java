package com.clotheshop.service.impl;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clotheshop.service.IProductService;

@Component
public class MyFilter implements Filter {
	
	@Autowired
	private IProductService prodSer;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpRequest= (HttpServletRequest) request;
		HttpServletResponse  myResponse= (HttpServletResponse) response;
		if(httpRequest.getRequestURL().toString().endsWith("")) {
			httpRequest.getSession().setAttribute("count", prodSer.countItemInCart(httpRequest));
			chain.doFilter(httpRequest, myResponse);
		}
	}

}
