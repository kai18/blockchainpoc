package com.kaustubh.blockchain.controller;

import com.kaustubh.blockchain.model.UserSignupRequest;
import com.kaustubh.blockchain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAccessController {

  UserService userService;

  @Autowired
  public UserAccessController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/user")
  public void createUser(UserSignupRequest userSignupRequest){
    this.userService.createUser(userSignupRequest);
  }

}
