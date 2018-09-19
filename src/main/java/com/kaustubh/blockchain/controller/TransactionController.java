package com.kaustubh.blockchain.controller;

import java.util.Objects;
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

import com.kaustubh.blockchain.model.Car;
import com.kaustubh.blockchain.model.Receipt;
import com.kaustubh.blockchain.model.Transaction;
import com.kaustubh.blockchain.service.CarService;
import com.kaustubh.blockchain.service.TransactionService;

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

	@PostMapping("/buyCar")
	public ResponseEntity<String> buyCar(@RequestBody Transaction transactionRequest) {

		Receipt receipt = transactionService.buyCar(transactionRequest);
		return ResponseEntity.ok(receipt.getBlockNumber().toString());
	}

	@GetMapping("/getCar")
	public ResponseEntity<Car> getCar(@RequestParam String vin) throws Exception {
		Car car = transactionService.getCar(vin);

		if(Objects.isNull(car))
			car = carService.getCar(vin);

		return ResponseEntity.ok().body(car);
	}

}
