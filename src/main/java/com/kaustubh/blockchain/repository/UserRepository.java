package com.kaustubh.blockchain.repository;

import com.kaustubh.blockchain.model.User;
import java.util.Optional;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface UserRepository extends Neo4jRepository<User, String> {

  Optional<User> findByEmail(String email);

}
