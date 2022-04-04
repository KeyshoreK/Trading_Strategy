package com.acme.mytrader.callback;

import com.acme.mytrader.price.PriceListener;

import java.util.*;

public class StockServer implements Runnable {

    private final Map<PriceListener, SLHolder> listeners_ = new HashMap<>();
    private final Object lock = new Object();

    public void addPriceListener(PriceListener listener, String stock, double limit) {
        synchronized (lock) {
            SLHolder slHolder = new SLHolder(listener, stock, limit);
            listeners_.put(listener, slHolder);
        }
    }

    public void removePriceListener(PriceListener listener) {
        synchronized (lock) {
            listeners_.remove(listener);
        }
    }

    //server thread.
    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        while (true) {
            String stockName = "ACME:EUR";
            double newValue = 54.00;
            //if some stock has changed
            //assign to stockName and newValue
            //see if any listeners are interested.
            synchronized (listeners_) {
                final Enumeration<PriceListener> e = Collections.enumeration(listeners_.keySet());
                while (e.hasMoreElements()) {
                    PriceListener key = e.nextElement();
                    SLHolder slHolder = listeners_.get(key);
                    slHolder.updateIfRequired(stockName, newValue);
                }
            }
        }
    }
}
