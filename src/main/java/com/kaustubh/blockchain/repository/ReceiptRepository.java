package com.kaustubh.blockchain.repository;

import com.kaustubh.blockchain.model.Receipt;
import java.io.Serializable;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ReceiptRepository extends Neo4jRepository<Receipt, Serializable> {

}
