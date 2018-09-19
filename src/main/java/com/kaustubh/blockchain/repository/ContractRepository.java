package com.kaustubh.blockchain.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.web3j.tx.Contract;

public interface ContractRepository extends Neo4jRepository<Contract, String> {

}
