package com.clotheshop.config;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		authorities.forEach(authority -> {
			if (authority.getAuthority().equals("USER")) {
				try {
					response.sendRedirect("/shop");
				} catch (Exception e) {
				}
			} else if (authority.getAuthority().equals("ADMIN")) {
				try {
					response.sendRedirect("/admin");
				} catch (Exception e) {
				}
			} else {
				throw new IllegalStateException();
			}

		});

	}

}
