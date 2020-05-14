package com.indra.security.util;

public class Constants 
{
	// SPRING

	public static final String LOGIN_URL = "/login";
	public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
	public static final String TOKEN_BEARER_PREFIX = "Bearer ";
	public static final String VALIDATE_URL = "/validate";

	// JWT
	public static final String ISSUER_INFO = "https://www.indra.com/";
	public static final String SECRET_KEY = "1234";
	public static final long TOKEN_EXPIRATION_TIME = 864_000_000; // 10 day

}
