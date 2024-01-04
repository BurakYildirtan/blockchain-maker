package com.albsig.professionals.abgabe.model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Model to represent one block.
 */
public class Block {

  private static final Logger LOGGER = LogManager.getLogger(Block.class);
  private static final String HASH_ALGORITHM = "SHA-1";
  private static final String CHARACTER_SET = "UTF-8";
  private static final String STRING_FORMAT = "%02x";

  private int id;
  private String hash;
  private String previousHash;
  private String timestamp;
  private String data;
  private String userPublicKey;
  private int prefix;
  private int nonce;
  private MessageDigest digest = null;

  /**
   * Constructor.
   */
  public Block(int id, String userPublicKey, String previousHash, String data, int prefix) {
    try {
      this.id = id;
      this.userPublicKey = userPublicKey;
      this.previousHash = previousHash;
      this.data = data;
      this.timestamp = Long.toString(System.currentTimeMillis());
      this.nonce = 0;
      this.prefix = prefix;
      this.digest = MessageDigest.getInstance(HASH_ALGORITHM);
      this.hash = generateHash();
      mineBlock();
    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
      LOGGER.error(e);
    }
  }

  private String mineBlock() throws NoSuchAlgorithmException, UnsupportedEncodingException {
    if (prefix > 0) {
      String prefixString = generatePrefix();
      while (!hash.substring(0, prefix).equals(prefixString)) {
        nonce++;
        hash = generateHash();
      }
      return hash;
    } else {
      LOGGER.info("Block has no prefix");
      return hash;
    }
  }

  private String generatePrefix() {
    String result = "";
    int i = 0;
    while (i < prefix) {
      result += "0";
      i++;
    }
    return result;
  }

  /**
   * Generate hash for model.
   */
  public String generateHash() throws UnsupportedEncodingException {
    String allData = previousHash + timestamp + data + nonce;
    byte[] digestHash = digest.digest(allData.getBytes(CHARACTER_SET));
    String hash = generateHashFromByteArray(digestHash);
    return hash;
  }

  private String generateHashFromByteArray(byte[] digestHash) {
    StringBuffer buffer = new StringBuffer();
    for (byte b : digestHash) {
      buffer.append(String.format(STRING_FORMAT, b));
    }
    return buffer.toString();
  }

  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }

  public int getId() {
    return id;
  }

  public String getPreviousHash() {
    return previousHash;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public String getUserPublicKey() {
    return userPublicKey;
  }

  public String getData() {
    return data;
  }

  public int getNonce() {
    return nonce;
  }

  public int getPrefix() {
    return prefix;
  }

  @Override
  public String toString() {
    return "Block [ " + id + ", hash=" + hash + ", previousHash=" + previousHash + ", timestamp="
        + timestamp + ", data=" + data + ", userId=" + userPublicKey + ", prefix=" + prefix
        + ", nonce=" + nonce + "]";
  }


}
