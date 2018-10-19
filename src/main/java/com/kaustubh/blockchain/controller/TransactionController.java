package com.kaustubh.blockchain.controller;

import com.kaustubh.blockchain.model.AssetTransaction;
import com.kaustubh.blockchain.model.CarInformation;
import com.kaustubh.blockchain.service.CarService;
import com.kaustubh.blockchain.service.TransactionService;
import java.io.IOException;
import java.security.spec.InvalidKeySpecException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class TransactionController {

  public final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

  CarService carService;
  TransactionService transactionService;

  @Autowired
  public TransactionController(CarService carService, TransactionService transactionService) {
    super();
    this.carService = carService;
    this.transactionService = transactionService;
  }

  @PostMapping
  public ResponseEntity<String> confirmTransaction(@RequestBody AssetTransaction transaction)
      throws InvalidKeySpecException, IOException {
    return ResponseEntity.ok(this.transactionService.confirmTransaction(transaction));
  }

  @PostMapping("/buyCar")
  public ResponseEntity<String> buyCar(@RequestBody AssetTransaction assetTransactionRequest) {

    String receipt = transactionService.buyCar(assetTransactionRequest);
    return ResponseEntity.ok(receipt);
  }

  @GetMapping("/getCar")
  public ResponseEntity<CarInformation> getCar(@RequestParam String vin) throws Exception {
    CarInformation car = transactionService.getCar(vin);
    return ResponseEntity.ok().body(car);
  }

}
