package com.indra.security.model.service;

import static java.util.Collections.emptyList;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.indra.security.model.dao.IUserRepository;
import com.indra.security.model.entity.UserPassword;

@Service
public class UserService implements UserDetailsService, IUserService
{
	@Autowired
	private IUserRepository userRepository;
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException 
	{
		logger.info("UserService, buscando usuario : ".concat(userName));
		com.indra.security.model.entity.User user = userRepository.findByUsername(userName);
		
		if(user == null) 
		{
			String errorMessage = "Usuario ".concat(userName).concat(" no registrado en el sistema.");
			logger.error(errorMessage);
			throw new UsernameNotFoundException(errorMessage);
		}
		
		List<GrantedAuthority> authorities = user.getRoles().stream().
				map(role -> new SimpleGrantedAuthority(role.getName())).
				peek(authority -> logger.info("Role : ".concat(authority.getAuthority()))).collect(Collectors.toList());
		
		//return new org.springframework.security.core.userdetails.User(user.getUserName(), getUserPassword(user), user.getEnabled(), true, true, true, emptyList());
		UserDetailsResponse userResponse = new UserDetailsResponse(user.getUsername(), getUserPassword(user), emptyList());
		userResponse.addAttr("email", user.getEmail());
		userResponse.addAttr("phoneNumber", user.getPhoneNumber());
		return  userResponse;
	}
	
	/**
	 * 
	 * @param user instancia User
	 * @return password habilitado del usuario.
	 */
	public String getUserPassword(com.indra.security.model.entity.User user) 
	{
		if(user.getPaswordList() != null && !(user.getPaswordList().isEmpty())) 
		{
			for(UserPassword pass: user.getPaswordList()) 
			{
				if(pass.getEnabled().equals(Boolean.TRUE)) 
				{
					return pass.getPassword();
				}
			}
		}		
		return "";
	}

	@Override
	@Transactional(readOnly = true)
	public com.indra.security.model.entity.User findByUserName(String userName) 
	{
		return userRepository.findByUsername(userName);
	}

}
