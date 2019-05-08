package com.bootcamp.blackbriar.auth;

import com.bootcamp.blackbriar.business.UserService;
import com.bootcamp.blackbriar.business.UserServiceImpl;
import com.bootcamp.blackbriar.model.UserEntity;
import com.bootcamp.blackbriar.model.UserLoginRequestModel;
import com.bootcamp.blackbriar.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private final AuthenticationManager authenticationManager;

  public AuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
    try {
      UserLoginRequestModel credentials = new ObjectMapper()
        .readValue(req.getInputStream(), UserLoginRequestModel.class);

      return authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
          credentials.getEmail(),
          credentials.getPassword(),
          new ArrayList<>()
        )
      );
    } catch(IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest req,
                                       HttpServletResponse res,
                                       FilterChain chain,
                                       Authentication auth) throws IOException, ServletException {
    String username = ((User) auth.getPrincipal()).getUsername();
    Date tokenExpiration = new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME);

    String token = Jwts.builder()
      .setSubject(username)
      .setExpiration(tokenExpiration)
      .signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET)
      .compact();

    res.getWriter().write(SecurityConstants.TOKEN_PREFIX + token);
  }
}