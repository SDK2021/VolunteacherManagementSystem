package com.volunteacher.app.config.SecurityClasses;

public class SecurityConstant {
	
	public static final long EXPIRATION_TIME = 8_64_000000;
	public static final String TOKEN_PREFIX = "Bearer";
	public static final String JWT_TOKEN_HEADER = "Jwt-Token";
	public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
	public static final String FORBIDDEN_MESSAGE = "You need to Login to access this page";
	public static final String ACCESS_DENIED_MESSAGE = "You do not have permission to access this page";
	public static final String[] PUBLIC_URL = {"/vms/login","/vms/register","vms/donations"};
	
}
