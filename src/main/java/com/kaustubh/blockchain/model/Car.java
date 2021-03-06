package com.kaustubh.blockchain.model;

import java.io.Serializable;
import java.math.BigInteger;
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

  private String id;

  private String model;
  private String color;
  private String modelYear;
  private String company;

  public String getVin() {
    return vin;
  }

  public void setVin(String vin) {
    this.vin = vin;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  @Override
  public String toString() {
    return "Car{" +
        "vin='" + vin + '\'' +
        ", id='" + id + '\'' +
        ", model='" + model + '\'' +
        ", color='" + color + '\'' +
        ", modelYear='" + modelYear + '\'' +
        ", company='" + company + '\'' +
        '}';
  }
}
