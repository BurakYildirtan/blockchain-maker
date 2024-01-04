package com.albsig.professionals.abgabe.model;

import java.time.LocalDate;

/**
 * Model to represent one jewelry.
 */
public class Jewelry {
  private int jewelryId;
  private String manufacturer;
  private String name;
  private LocalDate releaseDate;

  /**
   * Constructor.
   */
  public Jewelry(int jewelryId, String name, String manufacturer, LocalDate releaseDate) {
    this.jewelryId = jewelryId;
    this.name = name;
    this.manufacturer = manufacturer;
    this.releaseDate = releaseDate;
  }

  public int getJewelryId() {
    return jewelryId;
  }

  public String getName() {
    return name;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public LocalDate getReleaseDate() {
    return releaseDate;
  }

  @Override
  public String toString() {
    return "Jewelry [jewelryId=" + jewelryId + ", manufacturer=" + manufacturer + ", name=" + name
        + ", releaseDate=" + releaseDate + "]";
  }
}
