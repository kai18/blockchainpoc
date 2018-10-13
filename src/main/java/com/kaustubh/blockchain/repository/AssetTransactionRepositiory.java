package com.kaustubh.blockchain.repository;

import com.kaustubh.blockchain.model.AssetTransaction;
import java.util.List;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface AssetTransactionRepositiory extends Neo4jRepository<AssetTransaction, String> {

  List<AssetTransaction> findAllBySeller(String seller);
}
