package com.kaustubh.blockchain.repository;

import com.kaustubh.blockchain.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface UserRepository extends Neo4jRepository<User, String> {

}
