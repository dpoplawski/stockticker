package com.stockticker.stocktickerjpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import java.io.IOException;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class StockController {
  @Autowired
  StockTickerRepo repository;

  private static final Logger log = LoggerFactory.getLogger(StockController.class);
  private final AtomicLong counter = new AtomicLong();

  @GetMapping("/stockTickerLookup")
  public StockTicker lookup(@RequestParam(required = false, defaultValue = "") String symbol) throws IOException {
    //set default values in case stock not found
    String low = "0";
    String high ="0";
    Boolean isValid = false;

    Date tempDate =  new java.sql.Date(Calendar.getInstance().getTime().getTime());
    Calendar today = Calendar.getInstance();
    Stock stock = YahooFinance.get(symbol, true);
    //check to make sure stock is valid and was not returned null
    if(stock != null && stock.isValid()){
      isValid = true;
      HistoricalQuote quote = stock.getHistory(today).get(0);
      low = quote.getLow().setScale(2, RoundingMode.HALF_UP).toString();
      high = quote.getHigh().setScale(2, RoundingMode.HALF_UP).toString();
    }
    // create new stock ticker and save to repo
    StockTicker st = new StockTicker(symbol, isValid, low, high, tempDate);
    repository.save(st);
    return st;
  }
}
