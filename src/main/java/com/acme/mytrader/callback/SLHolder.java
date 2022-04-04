package com.acme.mytrader.callback;

import com.acme.mytrader.price.PriceListener;

public class SLHolder {

    private PriceListener listener_;
    private String stockName_;
    private double limit_;

    public SLHolder(PriceListener listener_, String stockName_, double limit_){
        this.listener_ = listener_;
        this.stockName_ = stockName_;
        this.limit_ = limit_;
    }

    public void updateIfRequired(String stock, double val){
        if (stockName_.equals(stock) && val < limit_){
            //perform callback
            listener_.priceUpdate(stock, val);
        }
    }

    public PriceListener getListener_() {
        return listener_;
    }
}
