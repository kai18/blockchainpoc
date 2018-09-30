package com.kaustubh.blockchain.service;

import com.bigchaindb.api.AssetsApi;
import com.bigchaindb.builders.BigchainDbTransactionBuilder;
import com.bigchaindb.constants.Operations;
import com.bigchaindb.model.Asset;
import com.bigchaindb.model.Assets;
import com.bigchaindb.model.Transaction;
import com.kaustubh.blockchain.model.Car;
import com.kaustubh.blockchain.model.AssetTransaction;
import com.kaustubh.blockchain.repository.CarRepository;
import com.kaustubh.blockchain.repository.ReceiptRepository;
import java.io.IOException;
import java.security.KeyPair;
import java.util.List;
import net.i2p.crypto.eddsa.EdDSAPrivateKey;
import net.i2p.crypto.eddsa.EdDSAPublicKey;
import net.i2p.crypto.eddsa.KeyPairGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

  public String buyCar(AssetTransaction assetTransactionRequest) throws IOException {
    KeyPairGenerator keyPairGenerator = new KeyPairGenerator();
    KeyPair keyPair = keyPairGenerator.generateKeyPair();
    String amount  = "1";

    Car car = carService.getCar(assetTransactionRequest.getVin());
    //Transaction transferTransaction = BigchainDbTransactionBuilder.
      //  init().
       // addAssets(car.getBlockchainId(), String.class)


    return null;
  }


  public void createAsset(Car car) throws IOException{
    KeyPairGenerator keyPairGenerator = new KeyPairGenerator();
    KeyPair keyPair = keyPairGenerator.generateKeyPair();
    Transaction assetTransaction  =  BigchainDbTransactionBuilder
        .init().addAssets(car, Car.class)
        .operation(Operations.CREATE)
        .buildAndSign((EdDSAPublicKey) keyPair.getPublic(), (EdDSAPrivateKey)keyPair.getPrivate())
        .sendTransaction();

    LOGGER.info(assetTransaction.toString());
    car.setBlockchainId(assetTransaction.getId());
    carRepository.save(car);
  }

  public Car getCar(String vin) throws IOException {
    Assets assets = AssetsApi.getAssets(vin);
    List<Asset> asset = assets.getAssets();
    Car car = new Car();
    BeanUtils.copyProperties(assets.getAssets().get(0), car);

    car = Car.class.cast(assets.getAssets().get(0).getData());
    LOGGER.info(assets.getAssets().get(0).getData().toString());
    return car;
  }
}
