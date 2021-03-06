package com.kaustubh.blockchain.service;

import com.bigchaindb.api.AssetsApi;
import com.bigchaindb.api.TransactionsApi;
import com.bigchaindb.builders.BigchainDbTransactionBuilder;
import com.bigchaindb.constants.BigchainDbApi;
import com.bigchaindb.constants.Operations;
import com.bigchaindb.cryptoconditions.Fulfillment;
import com.bigchaindb.model.Asset;
import com.bigchaindb.model.Assets;
import com.bigchaindb.model.FulFill;
import com.bigchaindb.model.Transaction;
import com.bigchaindb.model.Transactions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kaustubh.blockchain.model.AssetTransaction;
import com.kaustubh.blockchain.model.Car;
import com.kaustubh.blockchain.model.CarInformation;
import com.kaustubh.blockchain.model.User;
import com.kaustubh.blockchain.repository.AssetTransactionRepositiory;
import com.kaustubh.blockchain.repository.CarInformationRepository;
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
  CarInformationRepository carInformationRepository;

  @Autowired
  public TransactionService(ReceiptRepository receiptRepository,
      CarService carService, CarRepository carRepository, UserService userService,
      AssetTransactionRepositiory assetTransactionRepositiory,
      CarInformationRepository carInformationRepository) {
    super();
    this.receiptRepository = receiptRepository;
    this.carService = carService;
    this.carRepository = carRepository;
    this.userService = userService;
    this.assetTransactionRepositiory = assetTransactionRepositiory;
    this.carInformationRepository = carInformationRepository;
  }

  public String confirmTransaction(AssetTransaction assetTransaction)
      throws InvalidKeySpecException, IOException {
    assetTransaction = assetTransactionRepositiory.findById(assetTransaction.getId()).get();
    User owner = userService.getUser(assetTransaction.getSeller());
    User buyer = userService.getUser(assetTransaction.getBuyer());
    String amount = "1";

    CarInformation carInformation = carInformationRepository.findById(assetTransaction.getVin()).get();
    Car car = new Car();
    BeanUtils.copyProperties(carInformation, car);
    car.setId(carInformation.getBlockchainId());
    String assetId = car.getId();

    final FulFill fulFill = new FulFill();
    fulFill.setTransactionId(assetId);
    fulFill.setOutputIndex("0");
    Transaction transferTransaction = BigchainDbTransactionBuilder.
        init().
        addInput(null, fulFill,
            KeyUtil.generatePublicKey(owner.getPublicKey()))
        .addOutput(amount, (KeyUtil.generatePublicKey(buyer.getPublicKey())))
        .addAssets(car.getId(), String.class)
        .operation(Operations.TRANSFER)
        .buildAndSignOnly(KeyUtil.generatePublicKey(owner.getPublicKey()),
            KeyUtil.generatePrivateKey(owner.getPrivateKey()));

    transferTransaction.setOperation(Operations.TRANSFER.toString());
    TransactionsApi.sendTransaction(transferTransaction);
    assetTransaction.setComplete(true);
    assetTransactionRepositiory.save(assetTransaction);
    carInformation.setCurrentOwner(owner.getEmail());
    return transferTransaction.getId();
  }

  public String buyCar(AssetTransaction assetTransactionRequest) {
    assetTransactionRequest.setId(UUID.randomUUID().toString());
    assetTransactionRepositiory.save(assetTransactionRequest);
    //this.confirmTransaction(assetTransactionRequest.getId());
    return assetTransactionRequest.toString();
  }


  public CarInformation createAsset(CarInformation carInformation)
      throws IOException, InvalidKeySpecException {
    User owner = userService.getUser(carInformation.getCurrentOwner());
    Car car = new Car();
    BeanUtils.copyProperties(carInformation, car);
    Transaction assetTransaction = BigchainDbTransactionBuilder
        .init().addAssets(car, Car.class)
        .operation(Operations.CREATE)
        .buildAndSign(KeyUtil.generatePublicKey(owner.getPublicKey()),
            KeyUtil.generatePrivateKey(owner.getPrivateKey()))
        .sendTransaction();

    LOGGER.info(assetTransaction.toString());
    carInformation.setModel(owner.getEmail());
    carInformation.setBlockchainId(assetTransaction.getId());
    return this.carInformationRepository.save(carInformation);
  }

  public CarInformation getCar(String vin) throws IOException {
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

    Transactions transactions = TransactionsApi
        .getTransactionsByAssetId(carInformationRepository.findById(vin).get().getBlockchainId(),
            Operations.CREATE);
    LOGGER.info(car.toString());
    CarInformation carInformation = carInformationRepository.findById(vin).get();
    BeanUtils.copyProperties(car, carInformation);

    for (Transaction transaction : transactions.getTransactions()) {
      carInformation.getPreviousOwners().addAll(transaction.getInputs().get(0).getOwnersBefore());
    }

    return carInformation;
  }
}
