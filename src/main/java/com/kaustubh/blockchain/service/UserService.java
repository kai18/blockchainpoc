package com.kaustubh.blockchain.service;

import com.kaustubh.blockchain.model.User;
import com.kaustubh.blockchain.model.UserSignupRequest;
import com.kaustubh.blockchain.repository.UserRepository;
import java.security.KeyPair;
import java.security.PrivateKey;
import net.i2p.crypto.eddsa.KeyPairGenerator;
import net.i2p.crypto.eddsa.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository){
    this.userRepository = userRepository;
  }

  public User createUser(UserSignupRequest userSignupRequest) {
    User user = new User();
    BeanUtils.copyProperties(userSignupRequest, user);
    KeyPairGenerator keyPairGenerator = new KeyPairGenerator();
    KeyPair keyPair = keyPairGenerator.generateKeyPair();

    user.setPrivateKey(Utils.bytesToHex(keyPair.getPrivate().getEncoded()));
    user.setPublicKey(Utils.bytesToHex(keyPair.getPublic().getEncoded()));

    return userRepository.save(user);
  }

  public User getUser(String email){
    return userRepository.findByEmail(email)
        .orElseThrow(()-> new InvalidDataAccessResourceUsageException("User not present"));
  }
}
