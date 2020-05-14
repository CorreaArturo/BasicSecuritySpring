package com.indra.security.model.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
public class UserDetailsResponse extends User
{

	private Map<String, String> attrExtends = new HashMap<String,String>();
	
	public UserDetailsResponse(String username, String password,
			Collection<? extends GrantedAuthority> authorities) 
	{
		super(username, password, true, true, true, true, authorities);
	}	
	
	public UserDetailsResponse(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) 
	{
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}
	
	public void addAttr(String attrName, String attrValue) 
	{
		this.attrExtends.put(attrName, attrValue);
	}
	
	public String getAttrValue(String attrName) 
	{
		return this.attrExtends.get(attrName);
	}

}
