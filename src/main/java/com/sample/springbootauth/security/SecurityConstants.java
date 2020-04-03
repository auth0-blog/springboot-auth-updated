package com.sample.springbootauth.security;

public class SecurityConstants {
  public static final String SECRET = "SecretKeyToGenJWTs"; // needs to get it from config
  public static final long EXPIRATION_TIME = 864_000_000; // 10 days
  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String HTTP_AUTHORIZATION_HEADER = "Authorization";
  public static final String SIGN_UP_URL = "/users/sign-up";

}
