package com.bootcamp.blackbriar.controller;

import com.bootcamp.blackbriar.service.group.GroupService;
import com.bootcamp.blackbriar.service.user.UserService;
import com.bootcamp.blackbriar.model.user.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
public class UserController {
  @Autowired
  UserService userService;

  @Autowired
  GroupService groupService;

  @Autowired
  ModelMapper modelMapper;

  @GetMapping(value = "/{userId}")
  public UserProfile getUser(@PathVariable String userId) {
    UserEntity user = userService.getUserByPublicId(userId);

    return modelMapper.map(user, UserProfile.class);
  }

  @GetMapping(value = "/me")
  public UserProfile whoAmI(Principal auth) {
    UserEntity me = userService.getUserByPublicId(auth.getName());

    return modelMapper.map(me, UserProfile.class);
  }

  @PostMapping
  public UserProfile createUser(@RequestBody UserDetailsRequestModel userDetails) {
    UserProfile returnValue = new UserProfile();
    UserDto userDto = new UserDto();

    BeanUtils.copyProperties(userDetails, userDto);

    UserDto createdUser = userService.createUser(userDto);

    BeanUtils.copyProperties(createdUser, returnValue);

    return returnValue;
  }
}
