package com.kaustubh.blockchain.service;

import com.bigchaindb.builders.BigchainDbTransactionBuilder;
import com.bigchaindb.constants.Operations;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaustubh.blockchain.model.Car;
import com.kaustubh.blockchain.model.Receipt;
import com.kaustubh.blockchain.model.Transaction;
import com.kaustubh.blockchain.repository.CarRepository;
import com.kaustubh.blockchain.repository.ReceiptRepository;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyPair;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import net.i2p.crypto.eddsa.EdDSAPrivateKey;
import net.i2p.crypto.eddsa.EdDSAPublicKey;
import net.i2p.crypto.eddsa.KeyPairGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple5;

@Service
public class TransactionService {

  public final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

  ReceiptRepository receiptRepository;
  CarService carService;
  CarRepository carRepository;

  @Autowired
  public TransactionService(ReceiptRepository receiptRepository,
      CarService carService, CarRepository carRepository) {
    super();
    this.receiptRepository = receiptRepository;
    this.carService = carService;
    this.carRepository = carRepository;
  }

  public String buyCar(Transaction transactionRequest) throws IOException {
    TransactionReceipt receipt = null;
    Car car = null;
    try {

      car = carService.getCar(transactionRequest.getVin());

    } catch (DataRetrievalFailureException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }

    ObjectMapper objectMapper = new ObjectMapper();
    KeyPairGenerator keyPairGenerator = new KeyPairGenerator();
    KeyPair keyPair = keyPairGenerator.generateKeyPair();

    Map<String, String> assetData = objectMapper.convertValue(car, TreeMap.class);
    com.bigchaindb.model.Transaction transaction = BigchainDbTransactionBuilder
        .init().addAssets(car, Car.class)
        .operation(Operations.CREATE)
        .buildAndSign((EdDSAPublicKey) keyPair.getPublic(), (EdDSAPrivateKey)keyPair.getPrivate())
        .sendTransaction();

    return transaction.toString();
  }

  public Car getCar(String vin) {

    Tuple5<String, String, List<String>, BigInteger, BigInteger> carTuple = null;

    Car car = carService.getCar(vin);

    car.setVin(vin);
    carRepository.save(car);
    return car;

  }
}
