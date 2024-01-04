package com.albsig.professionals.abgabe;


import com.albsig.professionals.abgabe.common.CorruptBlockException;
import com.albsig.professionals.abgabe.ui.BlockchainMakerApp;
import com.albsig.proffesionals.abgabe.manager.BlockChainManager;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;



/**
 * Unit test for instantiate BlockChainMakerApp.
 */
public class BlockchainMakerAppTest {

  @Mock
  private BlockchainMakerApp blockchainMaker;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  /**
   * Check if application will be launched.
   */
  @Test
  void testInstanceApplication() {
    blockchainMaker.open();
    Mockito.verify(blockchainMaker).open();
  }

  /**
   * Check if block is saved in list.
   */
  @Test
  void testIsBlockSaved()
      throws NoSuchAlgorithmException, UnsupportedEncodingException, CorruptBlockException {

    BlockChainManager blockchainManager = new BlockChainManager();
    blockchainManager.createBlock("publicKey", "data", 3);

    Assert.assertEquals(blockchainManager.getBlockList().size(), 1);
  }

  /**
   * Check if block chain is valid.
   */
  @Test
  void testIsBlockChainValid()
      throws NoSuchAlgorithmException, UnsupportedEncodingException, CorruptBlockException {

    BlockChainManager blockchainManager = new BlockChainManager();
    blockchainManager.createBlock("publicKey", "data", 3);
    blockchainManager.createBlock("publicKey2", "data2", 1);
    blockchainManager.createBlock("publicKey2", "data4", 1);

    Assert.assertTrue(blockchainManager.isBlockChainValid());
  }

  /**
   * Check if block chain is reset.
   */
  @Test
  void testIsBlockChainReset()
      throws NoSuchAlgorithmException, UnsupportedEncodingException, CorruptBlockException {

    BlockChainManager blockchainManager = new BlockChainManager();
    blockchainManager.createBlock("publicKey", "data", 3);
    blockchainManager.createBlock("publicKey2", "data2", 1);
    blockchainManager.createBlock("publicKey2", "data4", 1);

    Assert.assertEquals(blockchainManager.getBlockList().size(), 3);

    blockchainManager.resetBlockChain();

    Assert.assertTrue(blockchainManager.getBlockList().isEmpty());
  }
}
