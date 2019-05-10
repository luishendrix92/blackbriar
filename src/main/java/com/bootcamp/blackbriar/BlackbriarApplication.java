package com.bootcamp.blackbriar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BlackbriarApplication {

  public static void main(String[] args) {
    SpringApplication.run(BlackbriarApplication.class, args);
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SpringApplicationContext springApplicationContext() {
    return new SpringApplicationContext();
  }

}
