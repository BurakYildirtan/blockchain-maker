package com.albsig.professionals.abgabe.model;

/**
 * Model to represent one user.
 */
public class User {
  private int id;
  private String userName;
  private String publicKey;

  /**
   * Constructor.
   */
  public User(int id, String userName, String publicKey) {
    this.id = id;
    this.userName = userName;
    this.publicKey = publicKey;
  }

  public int getId() {
    return id;
  }

  public String getUserName() {
    return userName;
  }

  public String getPublicKey() {
    return publicKey;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", userName=" + userName + ", publicKey=" + publicKey + "]";
  }

  public String blockDataToString(User user) {
    return "FROM " + this.toString() + "\n" + "TO " + user.toString();
  }
}
