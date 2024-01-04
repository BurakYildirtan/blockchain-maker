package com.albsig.proffesionals.abgabe.manager;

import com.albsig.professionals.abgabe.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Class manages users.
 */
public class UserManager {

  private List<User> userList;

  public UserManager() {
    this.userList = new ArrayList<User>();
    createUsers();
  }

  public List<User> getUserList() {
    return userList;
  }

  private void createUser(String name) {
    int uid = userList.size();
    String publicKey = String.valueOf(uid) + name + String.valueOf(System.currentTimeMillis());
    userList.add(new User(uid, name, publicKey));
  }

  private void createUsers() {
    createUser("Yildirtan");
    createUser("Rovcanin");
    createUser("Speier");
    createUser("Hermann");
  }

  public void getUserWithUserName() {

  }
}
