package com.albsig.professionals.abgabe.common;

/**
 * Individual exception for corrupt hash-blocks.
 */
public class CorruptBlockException extends Exception {
  private static final long serialVersionUID = 1L;

  public CorruptBlockException() {
    super();
  }
}
