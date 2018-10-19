package com.kaustubh.blockchain.controller;

import com.kaustubh.blockchain.model.UserSignupRequest;
import com.kaustubh.blockchain.repository.AssetTransactionRepositiory;
import com.kaustubh.blockchain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {

  UserService userService;
  AssetTransactionRepositiory assetTransactionRepositiory;

  @Autowired
  public UserController(UserService userService,
      AssetTransactionRepositiory assetTransactionRepositiory) {
    this.userService = userService;
    this.assetTransactionRepositiory = assetTransactionRepositiory;
  }

  @PostMapping("/user")
  public void createUser(@RequestBody UserSignupRequest userSignupRequest) {
    this.userService.createUser(userSignupRequest);
  }

  @GetMapping("/dashboard")
  public ResponseEntity<?> getPendingTransactions(@RequestParam String email) {
    return ResponseEntity.status(HttpStatus.OK).body(this.assetTransactionRepositiory.findAllBySeller(email));
  }

}
