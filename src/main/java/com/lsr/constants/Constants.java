package com.lsr.constants;

import java.text.SimpleDateFormat;

public class Constants {
	private Constants() {
	    throw new IllegalStateException("Constants class");
	}
	
	public static final String USER_TYPE = "user-type";
	public static final String INTERN_SLS = "INTERNAL_USER";
	public static final String AD1_ACCESS = "ad1Access";
	public static final String PUBLIC_ACCESS = "publicAccess";
	
	public static final String API_AD1_ACCESS_HOST = "api.ad1Access.host";
	public static final String API_PUBLIC_ACCESS_HOST    = "api.publicAccess.host";
	public static final String JWT_SECRET        = "jwt.secret";
	public static final String JWT_PREFIX        = "jwt.prefix";
	public static final String JWT_EXPIRED       = "jwt.expired";
	public static final String JWT_REFRESH_EXPIRED      = "jwt.refresh.expired";
	public static final String JWT_REFRESH_SECRET       = "jwt.refresh.secret";
	public static final String YES = "Y";
	public static final String NO = "N";
	public static final String ADD = "ADD";
	public static final String EDIT = "EDIT";
	
	public static final String NOT_FOUND        = " tidak ditemukan";
	public static SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
	public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy");
	public static SimpleDateFormat DATE_FORMAT1 = new SimpleDateFormat("dd-MM-yyyy");
	public static SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm");
	
	// TEST LOCAL Akses Template
//	public static final String TEMPLATE_USER_UPLOAD = "com/btpn/sharia/btpnservicemain/template/templateUserUpload.xlsx";
	
	// Build Jar Akses Template
	public static final String TEMPLATE_USER_UPLOAD = "template/templateUserUpload.xlsx";
	
	
	//Author
	public static final String AUTHORIZATION = "Authorization";
	
}
