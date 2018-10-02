package com.kaustubh.blockchain.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NotNull
@NodeEntity
public class Car implements Serializable {

  @NotNull
  @NotEmpty
  @Id
  private String vin;

  private String blockchainId;

  private String model;
  private String color;
  private String modelYear;
  private String company;
  private BigInteger lastSoldPrice;
  private BigInteger numberOfTimesSold;
  private BigInteger kilometersTravelled;

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

  public BigInteger getLastSoldPrice() {
    return lastSoldPrice;
  }

  public void setLastSoldPrice(BigInteger lastSoldPrice) {
    this.lastSoldPrice = lastSoldPrice;
  }

  public BigInteger getNumberOfTimesSold() {
    return numberOfTimesSold;
  }

  public void setNumberOfTimesSold(BigInteger numberOfTimesSold) {
    this.numberOfTimesSold = numberOfTimesSold;
  }

  public BigInteger getKilometersTravelled() {
    return kilometersTravelled;
  }

  public void setKilometersTravelled(BigInteger kilometersTravelled) {
    this.kilometersTravelled = kilometersTravelled;
  }

  @Override
  public String toString() {
    return "Car{" +
        "vin='" + vin + '\'' +
        ", blockchainId='" + blockchainId + '\'' +
        ", model='" + model + '\'' +
        ", color='" + color + '\'' +
        ", modelYear='" + modelYear + '\'' +
        ", company='" + company + '\'' +
        ", lastSoldPrice=" + lastSoldPrice +
        ", numberOfTimesSold=" + numberOfTimesSold +
        ", kilometersTravelled=" + kilometersTravelled +
        '}';
  }
}
