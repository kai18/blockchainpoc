package com.kaustubh.blockchain.repository;

import com.kaustubh.blockchain.model.CarInformation;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CarInformationRepository extends Neo4jRepository<CarInformation, String> {

}
