package com.bootcamp.blackbriar.business;

import com.bootcamp.blackbriar.model.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
  UserDto createUser(UserDto userDto);
  UserDto getUser(String email);
}
