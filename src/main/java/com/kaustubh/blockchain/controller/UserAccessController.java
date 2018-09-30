package com.kaustubh.blockchain.controller;

import com.kaustubh.blockchain.model.UserSignupRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAccessController {

  @PostMapping("/user")
  public void createUser(UserSignupRequest userSignupRequest){

  }

}
