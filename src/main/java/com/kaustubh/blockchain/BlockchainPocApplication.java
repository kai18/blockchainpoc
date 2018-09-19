package com.kaustubh.blockchain;

import com.bigchaindb.builders.BigchainDbConfigBuilder;
import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import com.kaustubh.blockchain.contract.CarTransaction;
import com.kaustubh.blockchain.repository.ContractRepository;

@SpringBootApplication
@EnableNeo4jRepositories
@EnableTransactionManagement
public class BlockchainPocApplication {

	@Autowired
	Web3j web3j;

	@Autowired
	ContractRepository contractRepository;

	@Value("$bigChainDbApiUrl")
	private String bigChainDbUrl;

	@Value("$token")
	private String apiToken;

	@Value("$apiKey")
	private String apiKey;

	public static void main(String[] args) {
		SpringApplication.run(BlockchainPocApplication.class, args);
	}

	@Bean
	public void config(){
		BigchainDbConfigBuilder.baseUrl(bigChainDbUrl).
				addToken("app_id", apiToken).addToken("app_key", apiKey).setup();
	}

	@Bean
	public Web3j getWeb3j() {
		return Web3j.build(new HttpService("http:localhost:7545"));
	}

	@Bean
	public CarTransaction getContract() throws Exception {
		Credentials creds = Credentials.create("2ebe4fda98ede8142885575a73294007e8563fc5b383b66b2f8711340af323b4");
		CarTransaction carTransaction = CarTransaction
				.deploy(web3j, creds, BigInteger.valueOf(0L), BigInteger.valueOf(1000000L)).send();

		return carTransaction;
	}
}
