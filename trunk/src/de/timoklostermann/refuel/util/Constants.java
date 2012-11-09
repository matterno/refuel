package de.timoklostermann.refuel.util;

public interface Constants {
	public static final String LOGIN_NAME = "loginName";
	public static final String LOGIN_PASSWORD = "loginPw";
	
	public static final String REGISTER_NAME = "registerName";
	public static final String REGISTER_EMAIL = "registerEmail";
	public static final String REGISTER_PW = "registerPw";
	public static final String REGISTER_PW_REPEAT = "registerPwRepeat";
	
	public static final String JSON_SUCCESS = "SUCCESS";
	public static final String JSON_ERROR = "ERROR";
	
	public static final int ERROR_NO_CONNECTION = 1;
	public static final int ERROR_UNEXPECTED = 2;
	
	public static final int ERROR_USER_EXISTS = 3;
	public static final int ERROR_USER_EXISTS_NOT = 4;
	public static final int ERROR_PW_WRONG = 5;
}
