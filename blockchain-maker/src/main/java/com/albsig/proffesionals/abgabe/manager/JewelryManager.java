package com.albsig.proffesionals.abgabe.manager;

import com.albsig.professionals.abgabe.model.Jewelry;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class manages jewelry.
 */
public class JewelryManager {

  private List<Jewelry> jewelryList;

  public JewelryManager() {
    this.jewelryList = new ArrayList<Jewelry>();
    createJewelrys();
  }

  public List<Jewelry> getJewelryList() {
    return jewelryList;
  }

  private Jewelry createJewelry(String name, String manufacturer, LocalDate localDate) {
    int generatedId = jewelryList.size();
    return new Jewelry(generatedId, name, manufacturer, localDate);
  }


  private void createJewelrys() {
    jewelryList.add(createJewelry("Daytona R2", "Rolex", LocalDate.of(1998, 10, 28)));
    jewelryList.add(createJewelry("Universe N1098", "Jacob & Co.", LocalDate.of(2015, 8, 10)));
    jewelryList
        .add(createJewelry("Crayon Special Edition", "Richard Mille", LocalDate.of(2022, 5, 15)));
    jewelryList.add(createJewelry("Day-Date", "Casio", LocalDate.of(2010, 1, 10)));
    jewelryList.add(createJewelry("Golden Age", "Pierre Cardin", LocalDate.of(1920, 1, 1)));
  }
}
