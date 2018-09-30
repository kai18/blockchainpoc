package com.kaustubh.blockchain.model;

import com.bigchaindb.model.Transaction;
import org.neo4j.ogm.annotation.Id;

public class AssetTransaction extends Transaction {

  @Id
  public String id;
  private String seller;
  private String buyer;
  private Long price;
  private String vin;

  public String getSeller() {
    return seller;
  }

  public void setSeller(String seller) {
    this.seller = seller;
  }

  public String getBuyer() {
    return buyer;
  }

  public void setBuyer(String buyer) {
    this.buyer = buyer;
  }

  public Long getPrice() {
    return price;
  }

  public void setPrice(Long price) {
    this.price = price;
  }

  public String getVin() {
    return vin;
  }

  public void setVin(String vin) {
    this.vin = vin;
  }

}