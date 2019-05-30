package com.bootcamp.blackbriar.auth;

import java.util.Arrays;

import com.bootcamp.blackbriar.service.user.UserService;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
  private final UserService userDetailsService;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userDetailsService = userDetailsService;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager());

    authenticationFilter.setFilterProcessesUrl(SecurityConstants.SIGN_IN_URL);

    http
      .cors()
        .configurationSource(request -> {
          CorsConfiguration config = new CorsConfiguration()
            .applyPermitDefaultValues();

          config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
          config.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://localhost:8080", "https://blackbriar.site", "https://www.blackbriar.site"));
          
          return config;
        })
      .and()
      .csrf()
        .disable()
      .authorizeRequests()
      .antMatchers(
        HttpMethod.POST,
        SecurityConstants.SIGN_UP_URL,
        SecurityConstants.SIGN_IN_URL
      )
        .permitAll()
      .antMatchers("/api/**")
        .authenticated()
      .anyRequest()
        .permitAll()
      .and()
      .addFilter(authenticationFilter)
      .addFilter(new AuthorizationFilter(authenticationManager()))
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
      .userDetailsService(userDetailsService)
      .passwordEncoder(bCryptPasswordEncoder);
  }
}
