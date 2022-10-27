package com.lsr.common;

public interface CommonConstants {
	final static String SEPARATOR_DASH = "-";
	final static String SEPARATOR_SLASH = "/";
	
	final static String URL_LDAP = "https://accounts-beta.apps.btpnsyariah.com/auth/realms/beta/protocol/openid-connect/token/";
	//final static String URL_LDAP = "https://accounts-nww.apps.btpnsyariah.com/auth/realms/beta/protocol/openid-connect/token/";
	
	final String FILE_SEPARATOR = System.getProperty("file.separator");

	final String BOOLEAN_FALSE = "false";

	final String BOOLEAN_TRUE = "true";

	public static final String STRING_EMPTY = "";
	public static final String STRING_SUCCESS = "SUCCESS";
	public static final String STRING_FAILED = "FAILED";
	
	public static final String VALUE_SUCCESS = "SUCCESS";
	public static final String VALUE_ERROR = "ERROR";
}
