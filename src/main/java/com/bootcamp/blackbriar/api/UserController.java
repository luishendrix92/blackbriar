package com.bootcamp.blackbriar.api;

import com.bootcamp.blackbriar.model.UserDetailsRequestModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
  @GetMapping
  public String getUser() {
    return "GET request for a collection of users.";
  }

  @PostMapping
  public String createUser(@RequestBody UserDetailsRequestModel userDetails) {
    return "POST request to create new user.";
  }
}
