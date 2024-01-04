package com.albsig.professionals.abgabe.ui;

import com.albsig.professionals.abgabe.common.CorruptBlockException;
import com.albsig.professionals.abgabe.model.Block;
import com.albsig.professionals.abgabe.model.Jewelry;
import com.albsig.professionals.abgabe.model.User;
import com.albsig.proffesionals.abgabe.manager.BlockChainManager;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;


/**
 * Listener to create a hash-block.
 */
public class SelectionListenerCreateBlock extends SelectionAdapter {

  private static final Logger LOGGER = LogManager.getLogger(SelectionListenerCreateBlock.class);
  private static final int PREFIX = 3;

  private Display display;
  private ScrolledComposite scrolledComposite;
  private Composite content;
  private BlockChainManager blockChainManager;
  private List<Label> labelList;
  private Combo[] userComboArr;
  private Combo jewelryCombo;
  private Text priceText;

  /**
   * Constructor.
   */
  public SelectionListenerCreateBlock(Display display, ScrolledComposite scrolledComposite,
      Composite content, BlockChainManager blockChainManager, List<Label> labelBlockList,
      Combo[] userComboArr, Combo jewelryCombo, Text priceText) {
    this.display = display;
    this.scrolledComposite = scrolledComposite;
    this.content = content;
    this.blockChainManager = blockChainManager;
    this.labelList = labelBlockList;
    this.userComboArr = userComboArr;
    this.jewelryCombo = jewelryCombo;
    this.priceText = priceText;
  }

  /**
   * When button clicked the hash-block should be generated and added to the list.
   */
  public void widgetSelected(SelectionEvent e) {
    User fromUser = getUser(userComboArr[0]);
    User toUser = getUser(userComboArr[1]);
    Jewelry selectedJewelry = getJewelry(jewelryCombo);
    String price = getPrice();

    String data = "DATA [ FROM - " + fromUser.toString() + "\nTO - " + toUser.toString() + "\n"
        + selectedJewelry.toString() + "\n PRICE: " + price + "]";

    try {
      blockChainManager.createBlock(fromUser.getPublicKey(), data, PREFIX);
      Block newCreatedBlock = blockChainManager.getLastBlockFromList();

      if (newCreatedBlock != null) {
        initBlockLabel(newCreatedBlock);
        setContentToScroll();
      }
    } catch (NoSuchAlgorithmException | UnsupportedEncodingException | CorruptBlockException e1) {
      LOGGER.error(e);
    }
  }

  private User getUser(Combo combo) {
    String label = combo.getItem(combo.getSelectionIndex());
    User user = (User) combo.getData(label);
    return user;
  }

  private Jewelry getJewelry(Combo combo) {
    String label = combo.getItem(combo.getSelectionIndex());
    Jewelry jewelry = (Jewelry) combo.getData(label);
    return jewelry;
  }

  private String getPrice() {
    return priceText.getText() + "€";
  }

  private void initBlockLabel(Block block) {
    Label label = new Label(content, SWT.None);
    label.setFont(new Font(display, "Arial", 12, SWT.BOLD));
    label.setText(setBlockLabelText(block));
    label.setBackground(new Color(222, 138, 13));
    labelList.add(label);
    LOGGER.info("new block label created and added to list");
  }

  private void setContentToScroll() {
    scrolledComposite.setContent(content);
    content.layout();
    content.pack();
    scrolledComposite.layout();
  }

  private String setBlockLabelText(Block block) {
    return "PublicKey : " + block.getUserPublicKey() + "\nPrevious Hash : "
        + block.getPreviousHash() + "\nHash : " + block.getHash() + "\nData: " + block.getData()
        + "\nNonce: " + String.valueOf(block.getNonce()) + "\nPrefix: "
        + String.valueOf(block.getPrefix());
  }
}
