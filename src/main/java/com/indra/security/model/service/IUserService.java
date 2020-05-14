package com.indra.security.model.service;

import com.indra.security.model.entity.User;

public interface IUserService 
{
	public User findByUserName(String userName);
}
