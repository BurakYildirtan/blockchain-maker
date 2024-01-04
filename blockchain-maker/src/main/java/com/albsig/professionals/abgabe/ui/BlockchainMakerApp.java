package com.albsig.professionals.abgabe.ui;

import com.albsig.professionals.abgabe.model.Jewelry;
import com.albsig.professionals.abgabe.model.User;
import com.albsig.proffesionals.abgabe.manager.BlockChainManager;
import com.albsig.proffesionals.abgabe.manager.JewelryManager;
import com.albsig.proffesionals.abgabe.manager.UserManager;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * This class creates the application.
 */
public class BlockchainMakerApp {

  private BlockChainManager blockChainManager;
  private UserManager userManager;
  private JewelryManager jewelryManager;
  private Display display;
  private Shell shell;
  private Composite leftComposite;
  private ScrolledComposite rightCompositeScroll;
  private Composite content;
  private Button generateBlockBtn;
  private Button resetBlockChainBtn;
  private Combo[] userComboArr;
  private Combo jewelryCombo;
  private Text priceText;
  private List<Label> labelBlockList = new ArrayList<Label>();
  private Boolean grabExcessHorizontalSpace = true;
  private Boolean grabExcessVerticalSpace = true;

  /**
   * Constructor for class.
   */
  public BlockchainMakerApp(BlockChainManager blockChainManager, UserManager userManager,
      JewelryManager jewelryManager) {
    this.blockChainManager = blockChainManager;
    this.userManager = userManager;
    this.jewelryManager = jewelryManager;
    initDisplay();
    initShell();
    initLeftSide();
  }

  private void initLeftSide() {
    initComboBoxList();
    initJewleryPriceLabel();
    initJewelryCombo();
    createText();
    initGenerateBlockBtn();
    initResetHashListBtn();
  }

  private void initDisplay() {
    this.display = new Display();
  }

  private void initShell() {
    int noOfCols = 3;
    boolean sameWidth = true;
    shell = new Shell(display);
    GridLayout gridShell = new GridLayout(noOfCols, sameWidth);
    shell.setLayout(gridShell);

    leftComposite = new Composite(shell, SWT.NONE);
    leftComposite.setLayout(new GridLayout(2, true));

    GridData leftData = new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1);
    leftComposite.setLayoutData(leftData);

    rightCompositeScroll = new ScrolledComposite(shell, SWT.V_SCROLL);
    GridData rightData = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
    rightCompositeScroll.setExpandHorizontal(true);
    rightCompositeScroll.setLayoutData(rightData);
    rightCompositeScroll.setBackground(new Color(display, 202, 202, 202));
    initContentComposite();
  }

  private void initComboBoxList() {
    initComboBoxLabels();
    userComboArr = new Combo[2];
    for (int i = 0; i < userComboArr.length; i++) {
      userComboArr[i] = initCombo();
    }

  }

  private void initComboBoxLabels() {
    initLabel("From :", leftComposite);
    initLabel("To :", leftComposite);
  }

  private Label initLabel(String title, Composite composite) {
    Label newLabel = new Label(composite, SWT.LEFT);
    newLabel.setFont(new Font(display, "Arial", 12, SWT.BOLD));

    GridData gridData = new GridData(SWT.FILL, SWT.FILL, grabExcessHorizontalSpace, false, 1, 1);
    newLabel.setLayoutData(gridData);
    newLabel.setText(title);
    return newLabel;

  }

  private Combo initCombo() {
    Combo comboBox = new Combo(leftComposite, SWT.DROP_DOWN | SWT.READ_ONLY);
    comboBox.setFont(new Font(display, "Arial", 12, SWT.BOLD));

    GridData gridData =
        new GridData(SWT.FILL, SWT.FILL, grabExcessHorizontalSpace, grabExcessVerticalSpace, 1, 1);
    comboBox.setLayoutData(gridData);

    setUserItems(comboBox);
    return comboBox;
  }

  private void setUserItems(Combo comboBox) {
    for (int i = 0; i < userManager.getUserList().size(); i++) {
      User currUser = userManager.getUserList().get(i);
      String label = currUser.getPublicKey() + " - " + currUser.getUserName();
      comboBox.add(label);
      comboBox.setData(label, currUser);
    }
  }

  private void initJewleryPriceLabel() {
    initLabel("Jewelry :", leftComposite);
    initLabel("Price :", leftComposite);
  }

  private void initJewelryCombo() {
    jewelryCombo = new Combo(leftComposite, SWT.DROP_DOWN | SWT.READ_ONLY);
    jewelryCombo.setFont(new Font(display, "Arial", 12, SWT.BOLD));

    GridData gridData =
        new GridData(SWT.FILL, SWT.FILL, grabExcessHorizontalSpace, grabExcessVerticalSpace, 1, 1);
    jewelryCombo.setLayoutData(gridData);

    setJewelryItems(jewelryCombo);
  }

  private void setJewelryItems(Combo comboBox) {
    for (int i = 0; i < jewelryManager.getJewelryList().size(); i++) {
      Jewelry currJewelry = jewelryManager.getJewelryList().get(i);
      String label = currJewelry.getManufacturer() + " - " + currJewelry.getName();
      comboBox.add(label);
      comboBox.setData(label, currJewelry);
    }
  }

  private void createText() {
    priceText = new Text(leftComposite, SWT.MULTI | SWT.WRAP);
    priceText.setFont(new Font(display, "Arial", 12, SWT.BOLD));
    GridData gridData = new GridData(SWT.FILL, SWT.WRAP, grabExcessHorizontalSpace, false, 1, 1);
    gridData.heightHint = 60;
    priceText.setLayoutData(gridData);
  }

  private void initGenerateBlockBtn() {
    generateBlockBtn = new Button(leftComposite, SWT.PUSH);
    generateBlockBtn.setText("Generate Block");

    GridData gridData = new GridData(SWT.FILL, SWT.FILL, grabExcessHorizontalSpace, false, 2, 1);
    generateBlockBtn.setLayoutData(gridData);

    setGenerateBlockListener();

  }

  private void setGenerateBlockListener() {
    generateBlockBtn
        .addSelectionListener(new SelectionListenerCreateBlock(display, rightCompositeScroll,
            content, blockChainManager, labelBlockList, userComboArr, jewelryCombo, priceText));
  }

  private void initContentComposite() {
    content = new Composite(rightCompositeScroll, SWT.None);
    FillLayout fillLayout = new FillLayout(SWT.VERTICAL);
    fillLayout.spacing = 12;
    content.setLayout(fillLayout);
  }

  private void initResetHashListBtn() {
    resetBlockChainBtn = new Button(leftComposite, SWT.PUSH);
    resetBlockChainBtn.setText("Reset Blockchain");
    GridData gridData = new GridData(SWT.FILL, SWT.FILL, grabExcessHorizontalSpace, false, 2, 1);
    resetBlockChainBtn.setLayoutData(gridData);
    setResetBlockchainListener();
  }

  private void setResetBlockchainListener() {
    resetBlockChainBtn.addSelectionListener(new SelectionListenerResetBlockChain(
        rightCompositeScroll, content, blockChainManager, labelBlockList));
  }

  /**
   * Method to open / dispose shell.
   */
  public void open() {
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
  }
}
