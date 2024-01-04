package com.albsig.professionals.abgabe.client;

import com.albsig.professionals.abgabe.ui.BlockchainMakerApp;
import com.albsig.proffesionals.abgabe.manager.BlockChainManager;
import com.albsig.proffesionals.abgabe.manager.JewelryManager;
import com.albsig.proffesionals.abgabe.manager.UserManager;


/**
 * Main.
 */
public class MainMyApp {

  /**
   * Main method.
   */
  public static void main(String[] args) {
    BlockChainManager blockChainManager = new BlockChainManager();
    UserManager userManager = new UserManager();
    JewelryManager jewelryManager = new JewelryManager();
    BlockchainMakerApp d = new BlockchainMakerApp(blockChainManager, userManager, jewelryManager);
    d.open();
  }
}
