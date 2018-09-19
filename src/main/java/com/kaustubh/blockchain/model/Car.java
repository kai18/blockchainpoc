package com.kaustubh.blockchain.model;

import java.math.BigInteger;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NotNull
@NodeEntity
public class Car {

  @NotNull
  @NotEmpty
  @Id
  private String vin;

  private String model;
  private String color;
  private String modelYear;
  private String company;

  private String currentOwner;
  private BigInteger lastSoldPrice;
  private List<String> previousOwners;
  private String lastOwner;
  private BigInteger numberOfTimesSold;

  public String getVin() {
    return vin;
  }

  public void setVin(String vin) {
    this.vin = vin;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getModelYear() {
    return modelYear;
  }

  public void setModelYear(String modelYear) {
    this.modelYear = modelYear;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getCurrentOwner() {
    return currentOwner;
  }

  public void setCurrentOwner(String currentOwner) {
    this.currentOwner = currentOwner;
  }

  public BigInteger getLastSoldPrice() {
    return lastSoldPrice;
  }

  public void setLastSoldPrice(BigInteger lastSoldPrice) {
    this.lastSoldPrice = lastSoldPrice;
  }

  public List<String> getPreviousOwners() {
    return previousOwners;
  }

  public void setPreviousOwners(List<String> previousOwners) {
    this.previousOwners = previousOwners;
  }

  public String getLastOwner() {
    return lastOwner;
  }

  public void setLastOwner(String lastOwner) {
    this.lastOwner = lastOwner;
  }

  public BigInteger getNumberOfTimesSold() {
    return numberOfTimesSold;
  }

  public void setNumberOfTimesSold(BigInteger numberOfTimesSold) {
    this.numberOfTimesSold = numberOfTimesSold;
  }

}
