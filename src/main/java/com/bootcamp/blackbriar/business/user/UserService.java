package com.bootcamp.blackbriar.business.user;

import com.bootcamp.blackbriar.model.user.UserDto;
import com.bootcamp.blackbriar.model.user.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
  UserDto createUser(UserDto userDto);
  UserDto getUser(String email);

  UserEntity getUserByPublicId(String userId);
}
