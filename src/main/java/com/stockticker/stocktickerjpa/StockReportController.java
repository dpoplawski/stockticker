package com.stockticker.stocktickerjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StockReportController {
  @Autowired
  StockTickerRepo repo;

  @GetMapping("/stocks")
  private List<StockTicker> getAllStocks() {
    return (List<StockTicker>) repo.findAll();
  }
  @GetMapping("/stocks/{symbol}")
  private List<StockTicker> getAllBySymbol( @PathVariable("symbol") String symbol) {
    return (List<StockTicker>) repo.findBySymbol(symbol);
  }
}
