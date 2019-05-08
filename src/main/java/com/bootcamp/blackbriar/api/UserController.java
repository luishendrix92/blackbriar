package com.bootcamp.blackbriar.api;

import com.bootcamp.blackbriar.business.UserService;
import com.bootcamp.blackbriar.model.UserDetailsRequestModel;
import com.bootcamp.blackbriar.model.UserDto;
import com.bootcamp.blackbriar.model.UserRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
  @Autowired
  UserService userService;

  @GetMapping
  public String getUser() {
    return "GET request for a collection of users.";
  }

  @PostMapping
  public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
    UserRest returnValue = new UserRest();
    UserDto userDto = new UserDto();

    BeanUtils.copyProperties(userDetails, userDto);

    UserDto createdUser = userService.createUser(userDto);

    BeanUtils.copyProperties(createdUser, returnValue);

    return returnValue;
  }
}
