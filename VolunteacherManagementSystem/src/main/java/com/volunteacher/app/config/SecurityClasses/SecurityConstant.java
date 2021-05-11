package com.volunteacher.app.config.SecurityClasses;

public class SecurityConstant {
	
	public static final long EXPIRATION_TIME = 8_64_000000;
	public static final String TOKEN_PREFIX = "Bearer";
	public static final String JWT_TOKEN_HEADER = "Jwt-Token";
	public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
	public static final String FORBIDDEN_MESSAGE = "You need to Login to access this page";
	public static final String ACCESS_DENIED_MESSAGE = "You do not have permission to access this page";
	public static final String[] PUBLIC_URL = {"/vms/user-types","/vms/payment-result","/vms/payments","/vms/redirect-paytm","/vms/participants","/vms/events/*","/vms/events","/vms/applicant-requests","/vms/donors","/vms/user-types/*","/vms/email/*","/vms/updatePassword*","/vms/verifyOTP*","/vms/sendOTP"};	
}
