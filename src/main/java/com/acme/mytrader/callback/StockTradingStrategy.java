package com.acme.mytrader.callback;


import com.acme.mytrader.execution.ExecutionService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StockTradingStrategy {

    public static void main(String[] args) {

        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(cores);
        //Instantiate the class object from third party library.
        ExecutionService stockService = null;
        PriceSubscriber priceSubscriber = new PriceSubscriber(stockService);
        StockServer stockServer = new StockServer();
        stockServer.addPriceListener(priceSubscriber, "ACME:EUR", 55.00);
        executorService.submit(stockServer);
    }
}
