package com.sample.springbootauth.security.filters;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.springbootauth.user.ApplicationUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.sample.springbootauth.security.SecurityConstants.EXPIRATION_TIME;
import static com.sample.springbootauth.security.SecurityConstants.HTTP_AUTHORIZATION_HEADER;
import static com.sample.springbootauth.security.SecurityConstants.SECRET;
import static com.sample.springbootauth.security.SecurityConstants.TOKEN_PREFIX;

/*
 * When we add a new filter to Spring Security, we can explicitly define where in the filter chain we want
 * that filter or we can let the framework figure it out by itself.
 * By extending the filter provided within the security framework,
 * Spring can automatically identify the best place to put it in the security chain.
 *
 * This filter, which is provided by Spring Security, registers itself as the responsible for /login endpoint.
 * As such, whenever your backend API gets a request to /login, your specialization of this filter (i.e., JWTAuthenticationFilter) goes
 * into action and handles the authentication attempt (through the attemptAuthentication method).
 * */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private static final Logger logger = Logger.getAnonymousLogger();

  private AuthenticationManager authenticationManager;

  public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }


  /*
   * This is where we parse the user's credentials and issue them to the AuthenticationManager
   * */
  @Override
  public Authentication attemptAuthentication(HttpServletRequest req,
                                              HttpServletResponse res) throws AuthenticationException {
    try {
      ApplicationUser user = new ObjectMapper().readValue(req.getInputStream(), ApplicationUser.class);

      final UsernamePasswordAuthenticationToken usernamePasswordAuthentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>());
      return authenticationManager.authenticate(usernamePasswordAuthentication);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /*
   * This method called when a user successfully logs in. We use this method to generate a JWT for this user.
   * */
  @Override
  protected void successfulAuthentication(HttpServletRequest request,
                                          HttpServletResponse response,
                                          FilterChain chain,
                                          Authentication auth) throws IOException, ServletException {

    ApplicationUser applicationUser = (ApplicationUser) auth.getPrincipal();
    logger.info("Authentication succesfully for " + applicationUser);

    String token = JWT.create()
      .withSubject(applicationUser.getUsername())
      .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
      .sign(HMAC512(SECRET.getBytes()));
    response.addHeader(HTTP_AUTHORIZATION_HEADER, TOKEN_PREFIX + token);
  }
}
