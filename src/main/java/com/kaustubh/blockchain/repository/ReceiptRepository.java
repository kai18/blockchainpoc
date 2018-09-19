package com.kaustubh.blockchain.repository;

import java.io.Serializable;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.kaustubh.blockchain.model.Receipt;

public interface ReceiptRepository extends Neo4jRepository<Receipt, Serializable> {

}
