package com.kaustubh.blockchain.controller;

import com.kaustubh.blockchain.model.Car;
import com.kaustubh.blockchain.model.CarInformation;
import com.kaustubh.blockchain.service.CarService;
import com.kaustubh.blockchain.service.TransactionService;
import java.io.IOException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CarController {

  @Autowired
  CarService carService;

  @Autowired
  TransactionService transactionService;

  @PostMapping("register-car")
  public ResponseEntity<CarInformation> registerCar(@RequestBody CarInformation carInformation)
      throws IOException, InvalidKeySpecException {
    return ResponseEntity.ok().body(transactionService.createAsset(carInformation));
  }

  @GetMapping("get-cars")
  public List<CarInformation> getAllCars() {
    return carService.getAllCars();
  }

}
