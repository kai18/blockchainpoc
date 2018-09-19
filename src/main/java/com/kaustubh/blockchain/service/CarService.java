package com.kaustubh.blockchain.service;

import com.kaustubh.blockchain.model.Car;
import com.kaustubh.blockchain.repository.CarRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class CarService {

  CarRepository carRepository;

  @Autowired
  public CarService(CarRepository carRepository) {
    super();
    this.carRepository = carRepository;
  }

  public Car saveCar(Car car) {
    carRepository.findByVin(car.getVin())
        .orElseThrow(() -> new DuplicateKeyException("This car is already registered"));
    return carRepository.save(car);
  }

  public Car getCar(String vin) {
    return carRepository.findById(vin)
        .orElseThrow(() -> new DataRetrievalFailureException("No Car found with vin " + vin));
  }

  public List<Car> getAllCars() {
    return (List<Car>) (carRepository.findAll());
  }
}
