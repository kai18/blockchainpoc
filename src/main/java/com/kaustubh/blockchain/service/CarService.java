package com.kaustubh.blockchain.service;

import com.bigchaindb.builders.BigchainDbTransactionBuilder;
import com.bigchaindb.constants.Operations;
import com.bigchaindb.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaustubh.blockchain.model.Car;
import com.kaustubh.blockchain.model.CarInformation;
import com.kaustubh.blockchain.repository.CarInformationRepository;
import com.kaustubh.blockchain.repository.CarRepository;
import java.io.IOException;
import java.security.KeyPair;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import net.i2p.crypto.eddsa.EdDSAPrivateKey;
import net.i2p.crypto.eddsa.EdDSAPublicKey;
import net.i2p.crypto.eddsa.KeyPairGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class CarService {

  CarRepository carRepository;
  CarInformationRepository carInformationRepository;

  @Autowired
  public CarService(CarRepository carRepository, CarInformationRepository carInformationRepository) {
    super();
    this.carRepository = carRepository;
    this.carInformationRepository = carInformationRepository;
  }

  public Car saveCar(Car car) throws IOException {
    carRepository.findByVin(car.getVin()).ifPresent(c -> {
      throw new DuplicateKeyException("This car is already registered");
    });
    return carRepository.save(car);
  }

  public Car getCar(String vin) {
    return carRepository.findById(vin)
        .orElseThrow(() -> new DataRetrievalFailureException("No Car found with vin " + vin));
  }

  public List<CarInformation> getAllCars() {
    return (List<CarInformation>) carInformationRepository.findAll();
  }
}
