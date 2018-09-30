package com.kaustubh.blockchain.utils;

import java.security.Key;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import net.i2p.crypto.eddsa.EdDSAPrivateKey;
import net.i2p.crypto.eddsa.EdDSAPublicKey;
import net.i2p.crypto.eddsa.Utils;

public class KeyUtil {

  public static EdDSAPrivateKey generatePrivateKey(String key) throws InvalidKeySpecException {
    byte[] keyBytes = Utils.hexToBytes(key);
    PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
    EdDSAPrivateKey edDSAPrivateKey = new EdDSAPrivateKey(encodedKeySpec);
    return edDSAPrivateKey;
  }
  public static EdDSAPublicKey generatePublicKey(String key) throws InvalidKeySpecException {
    X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Utils.hexToBytes(key));
    EdDSAPublicKey edDSAPublicKey = new EdDSAPublicKey(x509EncodedKeySpec);
    return edDSAPublicKey;
  }

}
