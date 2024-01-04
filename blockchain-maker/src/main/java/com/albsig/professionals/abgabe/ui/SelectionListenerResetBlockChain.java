package com.albsig.professionals.abgabe.ui;

import com.albsig.proffesionals.abgabe.manager.BlockChainManager;
import java.util.List;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;


/**
 * Listener to reset a block chain.
 */
public class SelectionListenerResetBlockChain extends SelectionAdapter {

  private Composite content;
  private ScrolledComposite scrolledComposite;
  private BlockChainManager blockChainManager;
  private List<Label> labelList;

  /**
   * Constructor.
   */
  public SelectionListenerResetBlockChain(ScrolledComposite scrolledComposite, Composite content,
      BlockChainManager blockChainManager, List<Label> labelBlockList) {
    this.content = content;
    this.scrolledComposite = scrolledComposite;
    this.blockChainManager = blockChainManager;
    this.labelList = labelBlockList;
  }

  /**
   * When button clicked, block chain will be reset.
   */
  public void widgetSelected(SelectionEvent e) {
    clearLabelList();
    clearBlockchain();
    setContentToScroll();
  }

  private void clearLabelList() {
    for (Label label : labelList) {
      label.dispose();
    }
    labelList.clear();
  }

  private void clearBlockchain() {
    blockChainManager.resetBlockChain();
  }

  private void setContentToScroll() {
    scrolledComposite.setContent(content);
    content.layout();
    content.pack();
    scrolledComposite.layout();
  }
}
