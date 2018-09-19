package com.kaustubh.blockchain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kaustubh.blockchain.model.Car;
import com.kaustubh.blockchain.service.CarService;

@RestController
@CrossOrigin
public class CarController {

	@Autowired
	CarService carService;

	@PostMapping("register-car")
	public ResponseEntity<Car> registerCar(@RequestBody Car car) {
		Car savedCar = carService.saveCar(car);
		return ResponseEntity.ok().body(savedCar);
	}

	@GetMapping("get-cars")
	public List<Car> getAllCars() {
		return carService.getAllCars();
	}

}
