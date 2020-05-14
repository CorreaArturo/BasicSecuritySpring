package com.indra.security.model.service.auth;


import static com.indra.security.util.Constants.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.indra.security.model.service.CacheServiceRedis;
import com.indra.security.model.service.ICacheService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	UserDetailsService userService;

	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception 
	{
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() 
	{
		return new BCryptPasswordEncoder();
	}

	@Bean("authenticationManager")
	@Override
	public AuthenticationManager authenticationManager() throws Exception 
	{
		return super.authenticationManager();
	}
	
	@Bean("cacheService")
	public ICacheService cacheService() 
	{
		return new CacheServiceRedis();
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception 
	{
//		http.authorizeRequests().anyRequest().authenticated().and().csrf().disable().sessionManagement().
//		sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.cors().and()
		.csrf().disable()
		.authorizeRequests().antMatchers(HttpMethod.POST, LOGIN_URL).permitAll()
		.antMatchers(HttpMethod.POST, VALIDATE_URL).permitAll()
		.anyRequest().authenticated().and()
			.addFilter(new AuthenticationJwtFilter(authenticationManager()))
			.addFilter(new AuthorizationJwtFilter(authenticationManager()));		
		
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() 
	{
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}	
	
}
