package com.kaustubh.blockchain.model;

import java.util.ArrayList;
import java.util.List;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class CarInformation {

  @Id
  private String vin;
  private String blockchainId;
  private String model;
  private String color;
  private String modelYear;
  private String company;
  private Integer numberOfTimesSold;
  private String currentOwner;
  private List<String> previousOwners;

  public String getVin() {
    return vin;
  }

  public void setVin(String vin) {
    this.vin = vin;
  }

  public String getBlockchainId() {
    return blockchainId;
  }

  public void setBlockchainId(String blockchainId) {
    this.blockchainId = blockchainId;
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

  public Integer getNumberOfTimesSold() {
    return numberOfTimesSold;
  }

  public void setNumberOfTimesSold(Integer numberOfTimesSold) {
    this.numberOfTimesSold = numberOfTimesSold;
  }

  public String getCurrentOwner() {
    return currentOwner;
  }

  public void setCurrentOwner(String currentOwner) {
    this.currentOwner = currentOwner;
  }

  public List<String> getPreviousOwners() {
    if(previousOwners == null)
      previousOwners = new ArrayList<>();
    return previousOwners;
  }

  public void setPreviousOwners(List<String> previousOwners) {
    this.previousOwners = previousOwners;
  }
}
