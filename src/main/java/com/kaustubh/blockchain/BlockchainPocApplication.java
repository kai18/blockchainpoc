package com.kaustubh.blockchain;

import com.bigchaindb.builders.BigchainDbConfigBuilder;
import com.kaustubh.blockchain.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableNeo4jRepositories
@EnableTransactionManagement
public class BlockchainPocApplication {

  @Autowired
  ContractRepository contractRepository;

  @Value("${bigChainDbApiUrl}")
  private String bigChainDbUrl;

  @Value("${token}")
  private String apiToken;

  @Value("${apiKey}")
  private String apiKey;

  public static void main(String[] args) {
    SpringApplication.run(BlockchainPocApplication.class, args);
  }

  @EventListener(ApplicationReadyEvent.class)
  public void config() {
    BigchainDbConfigBuilder.baseUrl(bigChainDbUrl).
        addToken("app_id", apiToken).addToken("app_key", apiKey).setup();
  }

}
