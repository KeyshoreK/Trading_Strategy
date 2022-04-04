package com.acme.mytrader.strategy;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.price.PriceListener;

public class PriceClient implements PriceListener {

    private String stockName;
    private double limit;
    private ExecutionService service;

    public PriceClient(String stockName, double limit, ExecutionService service){
        this.stockName = stockName;
        this.limit = limit;
        this.service = service;
    }

    @Override
    public void priceUpdate(String security, double price) {
        if (security.equalsIgnoreCase(stockName) && price < limit){
            service.buy(security, price, 100);
        }
    }

    public String getStockName(){
        return stockName;
    }

    public double getLimit(){
        return limit;
    }
}
