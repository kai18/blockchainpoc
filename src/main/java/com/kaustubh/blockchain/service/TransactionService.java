package com.kaustubh.blockchain.service;

import com.kaustubh.blockchain.repository.CarRepository;
import java.math.BigInteger;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple5;

import com.kaustubh.blockchain.contract.CarTransaction;
import com.kaustubh.blockchain.model.Car;
import com.kaustubh.blockchain.model.Receipt;
import com.kaustubh.blockchain.model.Transaction;
import com.kaustubh.blockchain.repository.ReceiptRepository;

@Service
public class TransactionService {

	public final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	ReceiptRepository receiptRepository;
	CarTransaction carTransaction;
	CarService carService;
	CarRepository carRepository;

	@Autowired
	public TransactionService(ReceiptRepository receiptRepository, CarTransaction carTransaction,
			CarService carService, CarRepository carRepository) {
		super();
		this.receiptRepository = receiptRepository;
		this.carTransaction = carTransaction;
		this.carService = carService;
		this.carRepository = carRepository;
	}

	public Receipt buyCar(Transaction transactionRequest) {
		TransactionReceipt receipt = null;
		Car car = null;
		try {

			car = carService.getCar(transactionRequest.getVin());
			receipt = carTransaction.buy(transactionRequest.getSeller(), transactionRequest.getBuyer(),
					BigInteger.valueOf(transactionRequest.getPrice()), transactionRequest.getVin()).send();
		} catch (DataRetrievalFailureException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Receipt transactionReceipt = new Receipt();
		transactionReceipt.setCar(car);
		transactionReceipt.setBlockNumber(receipt.getBlockNumber());
		receiptRepository.save(transactionReceipt);

		return transactionReceipt;
	}

	public Car getCar(String vin) {

		Tuple5<String, String, List<String>, BigInteger, BigInteger> carTuple = null;
		try {
			carTuple = carTransaction.getCarInfo(vin).send();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Car car = carService.getCar(vin);

		car.setVin(vin);
		if(!carTuple.getValue1().equals("0x0000000000000000000000000000000000000000"))
			car.setCurrentOwner(carTuple.getValue1());
		car.setLastOwner(carTuple.getValue2());
		car.setPreviousOwners(carTuple.getValue3());
		car.setNumberOfTimesSold(carTuple.getValue4());
		car.setLastSoldPrice(carTuple.getValue5());
		carRepository.save(car);
		return car;

	}
}
