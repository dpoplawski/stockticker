package com.stockticker.stocktickerjpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.quotes.stock.StockQuote;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.Calendar;


@SpringBootApplication

public class StockTickerJpaApplication {

	private static final Logger log = LoggerFactory.getLogger(StockTickerJpaApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(StockTickerJpaApplication.class, args);
	}

	//provides testing and sanity check on build
	@Bean
	public CommandLineRunner demo(StockTickerRepo repository) {
		return (args) -> {
			// save a few stocks
			String low = "0";
			String high ="0";
			Boolean isValid = false;
			Date tempDate =  new java.sql.Date(Calendar.getInstance().getTime().getTime());
			Calendar today = Calendar.getInstance();

			/** test data **/
			String symbol1 = "goog";
			Stock stock1 = YahooFinance.get(symbol1, true);
			if(stock1 != null && stock1.isValid()){
				isValid = true;
				StockQuote quote = stock1.getQuote();
				low = quote.getDayLow().setScale(2, RoundingMode.HALF_UP).toString();
				high = quote.getDayHigh().setScale(2, RoundingMode.HALF_UP).toString();
			}
			StockTicker st1 = new StockTicker(symbol1, isValid, low, high, tempDate);
			repository.save(st1);


			String symbol2 = "aapl";
			Stock stock2 = YahooFinance.get(symbol2, true);
			if(stock2 != null && stock2.isValid()){
				isValid = true;
				StockQuote quote = stock2.getQuote();
				low = quote.getDayLow().setScale(2, RoundingMode.HALF_UP).toString();
				high = quote.getDayHigh().setScale(2, RoundingMode.HALF_UP).toString();
			}
			StockTicker st2 = new StockTicker(symbol2, isValid, low, high, tempDate);
			repository.save(st2);

			String symbol3 = "nke";
			Stock stock3 = YahooFinance.get(symbol3, true);
			if(stock3 != null && stock3.isValid()){
				isValid = true;
				StockQuote quote = stock3.getQuote();
				low = quote.getDayLow().setScale(2, RoundingMode.HALF_UP).toString();
				high = quote.getDayHigh().setScale(2, RoundingMode.HALF_UP).toString();
			}
			StockTicker st3 = new StockTicker(symbol2, isValid, low, high, tempDate);
			repository.save(st3);

			// fetch all stocks
			log.info("StockTicker found with findAll():");
			log.info("-------------------------------");
			for (StockTicker customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual stock by ID
			StockTicker stock = repository.findById(1L);
			log.info("Stock found with findById(1L):");
			log.info("--------------------------------");
			log.info(stock.toString());
			log.info("");

			// fetch stocks by symbol
			log.info("Stock found with findBySymbol('Symbol'):");
			log.info("--------------------------------------------");
			repository.findBySymbol("goog").forEach(test -> {
				log.info(test.toString());
			});
			log.info("");
		};
	}

}
