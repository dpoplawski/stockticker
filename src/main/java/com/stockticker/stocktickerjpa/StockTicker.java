package com.stockticker.stocktickerjpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class StockTicker {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;
  private String symbol;
  private Boolean isValid;
  private String highValue;
  private String lowValue;
  private Date dateLookup;

  //for JPA
  protected StockTicker() {}

  public StockTicker(String symbol, Boolean isValid, String lowValue, String highValue, Date dateLookup){
    this.symbol = symbol;
    this.isValid = isValid;
    this.lowValue = lowValue;
    this.highValue = highValue;
    this.dateLookup = dateLookup;
  }

  @Override
  public String toString() {
    return String.format(
            "StockTicker[ symbol='%s', isValid='%b', lowValue='%s', highValue='%s', dateLookup='%s']",
             symbol, isValid, lowValue, highValue, dateLookup.toString());
  }
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public Boolean getIsValid() {
    return isValid;
  }

  public void setisValid(Boolean isValid) {
    this.isValid = isValid;
  }

  public String getHighValue() {
    return highValue;
  }

  public void setHighValue(String highValue) {
    this.highValue = highValue;
  }

  public String getLowValue() {
    return lowValue;
  }

  public void setLowValue(String lowValue) {
    this.lowValue = lowValue;
  }

  public Date getDateLookup() {
    return dateLookup;
  }

  public void setDateLookup(Date dateLookup) {
    this.dateLookup = dateLookup;
  }
}
