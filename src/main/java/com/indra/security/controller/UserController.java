package com.indra.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.indra.security.model.dao.IUserRepository;
import com.indra.security.model.dto.UserInfo;
import com.indra.security.model.entity.User;
import com.indra.security.model.service.ISecurityManagerUserService;
import com.indra.security.util.RedisUtil;

@RestController
@ComponentScan("com.indra.security.model.service")
public class UserController 
{

	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private ISecurityManagerUserService managerUserService;
	
	@GetMapping("/users/{username}")
	public UserInfo getUser(@PathVariable String username) throws Exception
	{
		logger.info("UserController,  cerrando sesi\u00f3n");
		try 
		{
			return managerUserService.getUserInfo(username);
		} 
		catch (Exception e) 
		{
			logger.error("UserController, error obteniendo usuario.");
			throw new Exception("UserController, error obteniendo usuario.");
		}
	}
	
	@PostMapping("/logOut")
	public String logOut(@RequestParam(name="userName", required=true, defaultValue="") String userName) 
	{
		logger.info("UserController,  cerrando sesi\u00f3n");
		managerUserService.logOut(userName);
		return "".concat(userName);
	}
	
	@PostMapping("/isSessionActive")
	public String isSessionActive(@RequestParam(name="userName", required=true, defaultValue="") String userName) 
	{
		logger.info("UserController,  validando sesi\u00f3n");
		managerUserService.isSessionActive(userName);
		return "".concat(userName);
	}
	
	@PostMapping("/recoverPassword")
	public String recoverPassword(@RequestParam(name="userName", required=true, defaultValue="") String userName) 
	{
		logger.info("UserController, recuperando password");
		String recoverPage = "";
		try 
		{
			recoverPage = managerUserService.recoverPassword(userName);
		} 
		catch (Exception err)
		{
			logger.error("UserController, funcionalidad no implementada.", err);
		}
		return "".concat(recoverPage);
	}	
	
}
