package com.bootcamp.blackbriar.auth;

public class SecurityConstants {
  public static final long EXPIRATION_TIME = 864000000; // 10 Days (in milliseconds)
  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String HEADER_STRING = "Authorization";
  public static final String SIGN_UP_URL = "/api/users";
  public static final String TOKEN_SECRET = "jf9i4jgu83nfl0";
}
