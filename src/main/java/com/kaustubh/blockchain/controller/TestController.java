package com.kaustubh.blockchain.controller;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;

import com.kaustubh.blockchain.contract.CarTransaction;

@RestController
public class TestController {

	public final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	@Autowired
	Web3j web3j;

	@GetMapping("/deployContract")
	public String deployContract() throws Exception {

		web3j.blockObservable(true).subscribe(block -> {
			LOGGER.info("New block added {}", block.getBlock().getTransactionsRoot());
		});
		Credentials creds = Credentials.create("9187c405126581761a57418271ee4a7f64d116c9ef0c7929df3a69f7bd580271");
		CarTransaction carTransaction = CarTransaction
				.deploy(web3j, creds, BigInteger.valueOf(0L), BigInteger.valueOf(1000000L)).send();
		carTransaction.buy("f601550eceDB4C946a6DeA7C52965bde4cff579A", "4ef05b2a6a8a199A0f40D85925Bcc70Ffcd6f026",
				BigInteger.valueOf(27637L), "87623").send();
		carTransaction.buy("f601550eceDB4C946a6DeA7C52965bde4cff579A", "4ef05b2a6a8a199A0f40D85925Bcc70Ffcd6f026",
				BigInteger.valueOf(272337L), "87923").send();
		return web3j
				.ethGetBalance("0x356666fec724302c55dc7ab94a1db6daa32c4620", DefaultBlockParameter.valueOf("latest"))
				.send().getBalance().toString();
	}
}
