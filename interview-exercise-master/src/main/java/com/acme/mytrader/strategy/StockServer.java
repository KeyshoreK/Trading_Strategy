package com.acme.mytrader.strategy;

import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceSource;


public class StockServer implements Runnable {

    private PriceListener listener;

    private PriceSource priceSource;

    public StockServer(PriceListener listener) {
        this.listener = listener;
    }

    //server thread.
    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        while (true) {
            double newValue = 54.00;
            //monitor stock prices and update the listener,
            //if stock has moved out of range.
            listener.priceUpdate("ACME:EUR", newValue);
        }
    }
}
