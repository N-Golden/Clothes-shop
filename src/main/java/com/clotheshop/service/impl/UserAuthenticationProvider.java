package com.clotheshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.clotheshop.entities.User;
import com.clotheshop.repository.UserRepository;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {
	Logger logger = LoggerFactory.getLogger(UserAuthenticationProvider.class);

	@Autowired
	private UserRepository repository;

	public UserAuthenticationProvider(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		User user = repository.findByEmail(username);
		if (user == null) {
			throw new BadCredentialsException("Details not found");
		}
		if (password.equals(user.getPassword())) {
			return new UsernamePasswordAuthenticationToken(username, password, getUserRoles(user.getRole().getName()));
		} else {
			throw new BadCredentialsException("Password mismatch");
		}

	}

	private List<GrantedAuthority> getUserRoles(String userRole) {
		List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
		grantedAuthorityList.add(new SimpleGrantedAuthority(userRole));
		return grantedAuthorityList;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
