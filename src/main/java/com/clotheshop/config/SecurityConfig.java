package com.clotheshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String[] PUBLIC_MATCHERS = { "/web/assets/**" };

	@Autowired
	private AuthenticationSuccessHandler successHandler;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				.antMatchers(PUBLIC_MATCHERS).permitAll()
				.antMatchers("/").permitAll()
				.antMatchers("/admin").hasAuthority("ADMIN")
				.antMatchers("/cart/add").hasAuthority("USER")
				.antMatchers("/cart").hasAuthority("USER")
				.and().formLogin().loginPage("/login")
				.usernameParameter("email").passwordParameter("password").successHandler(successHandler)
				.failureUrl("/login?error").and();

	}
}
