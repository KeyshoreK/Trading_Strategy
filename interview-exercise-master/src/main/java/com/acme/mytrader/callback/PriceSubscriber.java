package com.acme.mytrader.callback;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.price.PriceListener;

public class PriceSubscriber implements PriceListener {

    ExecutionService executionService;

    public PriceSubscriber(ExecutionService executionService){
        this.executionService = executionService;
    }

    @Override
    public void priceUpdate(String security, double price) {
        executionService.buy(security, price, 100);
    }
}
