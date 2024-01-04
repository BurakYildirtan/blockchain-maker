package com.albsig.proffesionals.abgabe.manager;

import com.albsig.professionals.abgabe.common.CorruptBlockException;
import com.albsig.professionals.abgabe.model.Block;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class manages block chain and methods.
 */
public class BlockChainManager {

  private static final Logger LOGGER = LogManager.getLogger(BlockChainManager.class);
  private static final String ORIGIN = "0";
  private List<Block> blockList = new ArrayList<Block>();


  public BlockChainManager() {
    this.blockList = new ArrayList<Block>();
  }

  public List<Block> getBlockList() {
    return blockList;
  }

  /**
   * Constructor.
   */
  public Block getLastBlockFromList() {
    if (blockList.size() > 0) {
      return blockList.get(blockList.size() - 1);
    }
    return null;
  }

  /**
   * Create hash-block with data. Check if hashBlock and block chain is valid.
   *
   * @param publicKey user model public-Key.
   * @param data - data from users and price.
   * @param prefix - number of '0' in the beginning of hash.
   */
  public void createBlock(String publicKey, String data, int prefix)
      throws NoSuchAlgorithmException, UnsupportedEncodingException, CorruptBlockException {
    int generatedId = blockList.size();
    String previousHash = getPreviousHash();
    Block newBlock = new Block(generatedId, publicKey, previousHash, data, prefix);
    addBlockToList(newBlock);
  }

  private String getPreviousHash() {
    if (blockList.isEmpty()) {
      return "0";
    } else {
      int lastBlockIndex = blockList.size() - 1;
      Block lastBlock = blockList.get(lastBlockIndex);
      return lastBlock.getHash();
    }
  }

  private void addBlockToList(Block newBlock)
      throws CorruptBlockException, UnsupportedEncodingException {
    if (isBlockChainValid()) {
      if (blockList.size() > 0) {
        int lastBlockIndex = blockList.size() - 1;
        String previousBlockHash = blockList.get(lastBlockIndex).getHash();
        if (isPreviousHashValid(previousBlockHash, newBlock.getPreviousHash())
            && isHashValid(newBlock)) {
          blockList.add(newBlock);
          LOGGER.info("Block added to blockchain !", newBlock);
        } else {
          throw new CorruptBlockException();
        }
      } else if (isHashValid(newBlock)) {
        blockList.add(newBlock);
        LOGGER.info("Origin-block added to blockchain !", newBlock);
      } else {
        throw new CorruptBlockException();
      }
    }
  }

  /**
   * Create hash-block and check if block chain valid.
   *
   * @throw UnsupportedEncodingException when digest doesn't work.
   * @return shows if block chain is valid.
   */
  public Boolean isBlockChainValid() throws UnsupportedEncodingException {
    Boolean isValid = true;

    int index = 0;
    while (isValid && index < blockList.size()) {
      Block currBlock = blockList.get(index);
      Boolean hashValid = isHashValid(currBlock);

      Boolean previousHashValid;
      if (index == 0) {
        previousHashValid = isPreviousHashValid(ORIGIN, currBlock.getPreviousHash());
      } else {
        int lastBlockIndex = index - 1;
        String previousBlockHash = blockList.get(lastBlockIndex).getHash();
        previousHashValid = isPreviousHashValid(previousBlockHash, currBlock.getPreviousHash());
      }

      isValid = previousHashValid && hashValid;
      index++;
    }
    LOGGER.info("Blockchain is valid: ", isValid);
    return isValid;
  }

  private Boolean isPreviousHashValid(String previousBlockHash, String blockPreviousHash) {
    return previousBlockHash.equals(blockPreviousHash) ? true : false;
  }

  private Boolean isHashValid(Block currBlock) throws UnsupportedEncodingException {
    return currBlock.generateHash().equals(currBlock.getHash());
  }

  public void resetBlockChain() {
    blockList = new ArrayList<Block>();
  }
}
