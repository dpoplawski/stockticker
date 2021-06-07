package com.stockticker.stocktickerjpa;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StockTickerRepo extends CrudRepository<StockTicker, Long> {
  List<StockTicker> findBySymbol(String symbol);
  StockTicker findById(long id);
}
