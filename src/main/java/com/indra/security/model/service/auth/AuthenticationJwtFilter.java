package com.indra.security.model.service.auth;

import static com.indra.security.util.Constants.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.indra.security.model.entity.User;
import com.indra.security.model.service.CacheServiceRedis;
import com.indra.security.model.service.ICacheService;
import com.indra.security.model.service.IUserService;
import com.indra.security.model.service.UserDetailsResponse;
import com.indra.security.util.RedisUtil;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

@ComponentScan({"com.indra.security.model.service", "com.indra.security.model.service.auth"})
public class AuthenticationJwtFilter extends UsernamePasswordAuthenticationFilter 
{
	@Autowired
	private IUserService userService;
	
//	@Autowired
//	@Qualifier("cacheService")
	private ICacheService cacheService = new CacheServiceRedis();	
	
	private AuthenticationManager authenticationManager;

	public AuthenticationJwtFilter(AuthenticationManager authenticationManager) 
	{
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException 
	{
		try
		{

			String requestDecode = new String (Base64.decodeBase64(IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8.name())));
			
			User credenciales = new ObjectMapper().readValue(requestDecode, User.class);			
			
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credenciales.getUsername(), credenciales.getPassword(), new ArrayList<>()));
		} 
		catch (IOException err) 
		{
			throw new RuntimeException(err);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException 
	{
		
		UserDetailsResponse userResponse = (UserDetailsResponse) auth.getPrincipal();
		String userName = userResponse.getUsername();
		
		String token = Jwts.builder().setIssuedAt(new Date()).setIssuer(ISSUER_INFO)
				.setSubject(userName)
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
				.claim("email", userResponse.getAttrValue("email"))
				.claim("phoneNumber", userResponse.getAttrValue("phoneNumber"))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
		cacheService.addSimpleValue(userName, token);
		response.addHeader(HEADER_AUTHORIZACION_KEY, TOKEN_BEARER_PREFIX.concat(" ").concat(token) );
	}
}
