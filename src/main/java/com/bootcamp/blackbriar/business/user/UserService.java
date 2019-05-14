package com.bootcamp.blackbriar.business.user;

import com.bootcamp.blackbriar.model.user.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
  UserDto createUser(UserDto userDto);
  UserDto getUser(String email);
}
