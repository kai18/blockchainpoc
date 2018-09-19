package com.kaustubh.blockchain.repository;

import com.kaustubh.blockchain.model.Car;
import java.util.Optional;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CarRepository extends Neo4jRepository<Car, String> {

  public Optional<Car> findByVin(String vin);
}
