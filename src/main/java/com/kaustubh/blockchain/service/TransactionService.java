package com.kaustubh.blockchain.service;

import com.bigchaindb.api.AssetsApi;
import com.bigchaindb.builders.BigchainDbTransactionBuilder;
import com.bigchaindb.constants.Operations;
import com.bigchaindb.model.Asset;
import com.bigchaindb.model.Assets;
import com.bigchaindb.model.FulFill;
import com.bigchaindb.model.Transaction;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kaustubh.blockchain.model.AssetTransaction;
import com.kaustubh.blockchain.model.Car;
import com.kaustubh.blockchain.model.User;
import com.kaustubh.blockchain.repository.AssetTransactionRepositiory;
import com.kaustubh.blockchain.repository.CarRepository;
import com.kaustubh.blockchain.repository.ReceiptRepository;
import com.kaustubh.blockchain.utils.KeyUtil;
import java.io.IOException;
import java.lang.reflect.Type;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.UUID;
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
  UserService userService;
  AssetTransactionRepositiory assetTransactionRepositiory;

  @Autowired
  public TransactionService(ReceiptRepository receiptRepository,
      CarService carService, CarRepository carRepository, UserService userService,
      AssetTransactionRepositiory assetTransactionRepositiory) {
    super();
    this.receiptRepository = receiptRepository;
    this.carService = carService;
    this.carRepository = carRepository;
    this.userService = userService;
    this.assetTransactionRepositiory = assetTransactionRepositiory;
  }

  public String confirmTransaction(String transactionId)
      throws InvalidKeySpecException, IOException {
    AssetTransaction assetTransaction = assetTransactionRepositiory.findById(transactionId).get();
    User owner = userService.getUser(assetTransaction.getSeller());
    User buyer = userService.getUser(assetTransaction.getBuyer());
    String amount = "1";

    Car car = carService.getCar(assetTransaction.getVin());
    String assetId = car.getId();
    final FulFill fulFill = new FulFill();
    fulFill.setTransactionId(assetId);
    fulFill.setOutputIndex("0");
    Transaction transferTransaction = BigchainDbTransactionBuilder.
        init().
        addInput(null, fulFill,
            KeyUtil.generatePublicKey(owner.getPublicKey()))
        .addOutput(null, (KeyUtil.generatePublicKey(buyer.getPublicKey())))
        .addAssets(car, Car.class)
        .operation(Operations.TRANSFER)
        .buildAndSign(KeyUtil.generatePublicKey(owner.getPublicKey()),
            KeyUtil.generatePrivateKey(owner.getPrivateKey()))
        .sendTransaction();

    return transferTransaction.getId();
  }

  public String buyCar(AssetTransaction assetTransactionRequest)
      throws IOException, InvalidKeySpecException {
    assetTransactionRequest.setId(UUID.randomUUID().toString());
    assetTransactionRepositiory.save(assetTransactionRequest);
    //this.confirmTransaction(assetTransactionRequest.getId());
    return assetTransactionRequest.toString();
  }


  public void createAsset(Car car) throws IOException, InvalidKeySpecException {
    User owner = userService.getUser(
        "302a300506032b6570032100c49d35f0c8c4c2d9f35840850b378073a578122c74b03ae7333b4dd58037a1df");

    Transaction assetTransaction = BigchainDbTransactionBuilder
        .init().addAssets(car, Car.class)
        .operation(Operations.CREATE)
        .buildAndSign(KeyUtil.generatePublicKey(owner.getPublicKey()),
            KeyUtil.generatePrivateKey(owner.getPrivateKey()))
        .sendTransaction();

    LOGGER.info(assetTransaction.toString());
    car.setId(assetTransaction.getId());
    carRepository.save(car);
  }

  public Car getCar(String vin) throws IOException {
    Assets assets = AssetsApi.getAssets(vin);
    List<Asset> asset = assets.getAssets();
    Car car = new Car();
    if (assets.size() != 0) {
      Gson gson = new Gson();
      BeanUtils.copyProperties(assets.getAssets().get(0), car);
      Type type = new TypeToken<Car>() {
      }.getType();
      String carJson = gson.toJson(assets.getAssets().get(0).getData());
      car = gson.fromJson(carJson, Car.class);
    } else {
      car = carRepository.findByVin(vin).get();
    }

    /*Transactions transactions = TransactionsApi
        .getTransactionsByAssetId(carRepository.findById(car.getVin()).get().getId(),
            Operations.TRANSFER);*/
    LOGGER.info(car.toString());
    return car;
  }
}
