package com.indra.security.model.service;

import org.springframework.stereotype.Service;

import com.indra.security.model.dto.UserInfo;

/**
 * ISecurityManagerUserService
 * @author INDRA
 * @sice 11/05/2020
 */
@Service
public interface ISecurityManagerUserService 
{
	/**
	 * 
	 * @param userName identificador del usuario (id/nickname/email)
	 * @return ruta de redirecc\u00f3n.
	 */
	public String logOut(String userName);
	
	/**
	 * 
	 * @param userName identificador del usuario (id/nickname/email)
	 * @return TRUE sesi\u00f3n activa, FALSE en caso contrario.
	 */
	public boolean isSessionActive(String userName);
	
	/**
	 * 
	 * @param userName identificador del usuario (id/nickname/email)
	 * @return ruta de redirecc\u00f3n.
	 */
	public String recoverPassword(String userName) throws Exception;
	
	/**
	 * 
	 * @param userName identificador del usuario (id/nickname/email)
	 * @return instancia UserInfo.
	 */
	public UserInfo getUserInfo(String userName) throws Exception;
}
