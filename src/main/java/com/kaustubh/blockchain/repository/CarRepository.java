package com.kaustubh.blockchain.repository;

import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.kaustubh.blockchain.model.Car;

public interface CarRepository extends Neo4jRepository<Car, String> {

	public Optional<Car> findByVin(String vin);
}
