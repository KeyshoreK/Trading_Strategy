package com.acme.mytrader.strategy;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceSource;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */
public class TradingStrategy implements Runnable, PriceListener{

    private final String stock;

    private final double limit;

    private final ExecutionService service;

    private PriceSource priceSource;

    private static final Logger logger = LogManager.getLogger(TradingStrategy.class);

    public TradingStrategy(String stock, double limit, ExecutionService service) {
        this.stock = stock;
        this.limit = limit;
        this.service = service;
    }

    //server thread.
    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        while (true) {
            double newValue = 54.00;
            //monitor stock prices and update the listener,
            //if stock has moved out of range.
            try{
                priceUpdate(stock, newValue);
                logger.info("updated stock with new value.");
            }catch (Exception ex){
                if (logger.isEnabled(Level.DEBUG)){
                    logger.debug("unable to update the stock" + ex);
                }else{
                    logger.error("unable to update the stock" + ex);
                }
                throw new StockException("unable to update the stock");
            }
        }
    }

    @Override
    public void priceUpdate(String security, double price) {
        if (security.equalsIgnoreCase(stock) && price < limit){
            service.buy(security, price, 100);
        }
    }

    static class StockException extends RuntimeException {
        public StockException(String message){
            super(message);
        }
    }

    public static void main(String[] args) {
        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(cores);
        //Instantiate the class object from third party library.
        ExecutionService stockService = null;
        String stockName = "ACME:EUR";
        double limit = 55.0;
        //PriceListener listener = new PriceClient(stockName, limit, stockService);
        TradingStrategy stockServer = new TradingStrategy(stockName, limit, stockService);
        executorService.submit(stockServer);
    }
}
