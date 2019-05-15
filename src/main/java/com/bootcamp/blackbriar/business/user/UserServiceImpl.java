package com.bootcamp.blackbriar.business.user;

import com.bootcamp.blackbriar.business.Utils;
import com.bootcamp.blackbriar.model.user.UserDto;
import com.bootcamp.blackbriar.model.user.UserEntity;
import com.bootcamp.blackbriar.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  UserRepository userRepository;

  @Autowired
  BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  Utils utils;

  @Override
  public UserDto createUser(UserDto user) {
    if (userRepository.findByEmail(user.getEmail()) != null) {
      throw new RuntimeException("A user with the provided email already exists.");
    }

    UserEntity userEntity = new UserEntity();

    BeanUtils.copyProperties(user, userEntity);

    String publicUserId = utils.generateUserId(30);
    String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());

    userEntity.setEncryptedPassword(encryptedPassword);
    userEntity.setUserId(publicUserId);

    UserEntity storedUserDetails = userRepository.save(userEntity);
    UserDto returnValue = new UserDto();

    BeanUtils.copyProperties(storedUserDetails, returnValue);

    return returnValue;
  }

  @Override
  public UserDto getUser(String email) {
    UserEntity userEntity = userRepository.findByEmail(email);

    if (userEntity == null) {
      throw new UsernameNotFoundException(email);
    }

    UserDto returnValue = new UserDto();

    BeanUtils.copyProperties(userEntity, returnValue);

    return returnValue;
  }

  @Override
  public UserEntity getUserByPublicId(String userId) {
    UserEntity user = userRepository.findByUserId(userId);

    if (user == null) {
      throw new EntityNotFoundException("Could not find a user with the provided userId.");
    }

    return user;
  }

  /*
   * Luis: This method will help Spring Security find a user by username (email)
   * and make authentication claims with the provided encrypted password which
   * will be compared to an encoded password provided by the login request model.
   */
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserEntity userEntity = userRepository.findByEmail(email);

    if (userEntity == null) {
      throw new UsernameNotFoundException(email);
    }

    return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
  }
}
