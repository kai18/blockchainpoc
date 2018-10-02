package com.kaustubh.blockchain.service;

import com.bigchaindb.api.AssetsApi;
import com.bigchaindb.api.TransactionsApi;
import com.bigchaindb.builders.BigchainDbTransactionBuilder;
import com.bigchaindb.constants.Operations;
import com.bigchaindb.model.Asset;
import com.bigchaindb.model.Assets;
import com.bigchaindb.model.FulFill;
import com.bigchaindb.model.Transaction;
import com.bigchaindb.model.Transactions;
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
    User buyer = userService.getUser(assetTransaction.getSeller());
    String amount = "1";

    Car car = carService.getCar(assetTransaction.getVin());
    String assetId = car.getBlockchainId();
    final FulFill fulFill = new FulFill();
    fulFill.setTransactionId(assetId);
    fulFill.setOutputIndex("0");
    Transaction transferTransaction = BigchainDbTransactionBuilder.
        init().
        addInput(null, fulFill,
            KeyUtil.generatePublicKey(owner.getPublicKey()))
        .addOutput(null, (KeyUtil.generatePublicKey(buyer.getPublicKey())))
        .addAssets(car.getBlockchainId(), String.class)
        .operation(Operations.CREATE)
        .buildAndSign(KeyUtil.generatePublicKey(owner.getPublicKey()),
            KeyUtil.generatePrivateKey(owner.getPrivateKey()))
        .sendTransaction();

    return transferTransaction.getId();
  }

  public String buyCar(AssetTransaction assetTransactionRequest)
      throws IOException, InvalidKeySpecException {
    assetTransactionRepositiory.save(assetTransactionRequest);
    return assetTransactionRequest.toString();
  }


  public void createAsset(Car car) throws IOException, InvalidKeySpecException {
    User owner = userService.getUser(
        "302a300506032b65700321005bdc57cf7049971b947da80d9266ba86b48a735ee33c4452fe87c8f5ca492eaf");

    Transaction assetTransaction = BigchainDbTransactionBuilder
        .init().addAssets(car, Car.class)
        .operation(Operations.CREATE)
        .buildAndSign(KeyUtil.generatePublicKey(owner.getPublicKey()),
            KeyUtil.generatePrivateKey(owner.getPrivateKey()))
        .sendTransaction();

    LOGGER.info(assetTransaction.toString());
    car.setBlockchainId(assetTransaction.getId());
    carRepository.save(car);
  }

  public Car getCar(String vin) throws IOException {
    Assets assets = AssetsApi.getAssets(vin);
    List<Asset> asset = assets.getAssets();
    Car car = new Car();
    Gson gson = new Gson();
    BeanUtils.copyProperties(assets.getAssets().get(0), car);
    Type type = new TypeToken<Car>() {
    }.getType();
    String carJson = gson.toJson(assets.getAssets().get(0).getData());
    car = gson.fromJson(carJson, Car.class);

    Transactions transactions = TransactionsApi
        .getTransactionsByAssetId(car.getBlockchainId(), Operations.TRANSFER);
    LOGGER.info(car.toString());
    return car;
  }
}
