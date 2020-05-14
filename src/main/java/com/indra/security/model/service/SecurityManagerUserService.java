package com.indra.security.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.indra.security.model.dao.IUserRepository;
import com.indra.security.model.dto.UserInfo;
import com.indra.security.model.entity.User;
import com.indra.security.util.RedisUtil;

@Service
public class SecurityManagerUserService implements ISecurityManagerUserService 
{
	private Logger logger = LoggerFactory.getLogger(SecurityManagerUserService.class);

//	@Autowired
//	@Qualifier("cacheService")
	private ICacheService cacheService = new CacheServiceRedis();	
	
	@Autowired
	private IUserRepository userRepository;	

	@Override
	public String logOut(String userName) 
	{
		logger.info("Cerrando sesi\u00f3n usuario ".concat(userName));
		Long deleteResponse = new Long(-1);
		if(cacheService.existsValue(userName)) 
		{
			deleteResponse = cacheService.deleteValue(userName);
		}
		else 
		{
			logger.info("Sesi\u00f3n de usuario ".concat(userName).concat(" no activa actualmente"));
		}
		return "".concat(String.valueOf(deleteResponse));
	}


	@Override
	public boolean isSessionActive(String userName) 
	{
		return cacheService.existsValue(userName);
	}


	@Override
	public String recoverPassword(String userName) throws Exception
	{
		// TODO Auto-generated method stub
		throw new Exception("Implementaci\u00f3n no definida.");
	}


	@Override
	public UserInfo getUserInfo(String userName) throws Exception
	{
		logger.info("Obteniendo usuario ".concat(userName));
		User userEntity = userRepository.findByUsername(userName);
		if(userEntity == null) 
		{
			logger.error("Usuario ".concat(userName).concat(" no registrado."));
			throw new Exception("Error obtemiendo usuario");
		}
		else 
		{
			return new UserInfo(userEntity.getUsername(), userEntity.getEmail(), userEntity.getPhoneNumber());
		}
		
	}
	
}
