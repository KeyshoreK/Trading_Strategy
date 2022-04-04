package com.acme.mytrader.strategy;

import com.acme.mytrader.execution.ExecutionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class TradingStrategyTest {

    private String stock;

    private double limit;

    @Mock
    ExecutionService service;

    @Before
    public void setUp(){
        this.stock = "ACME:EUR";
        this.limit = 54.0;
    }

    @Test
    public void testPriceUpdate(){
        TradingStrategy tradingStrategy = new TradingStrategy(stock, limit, service);
        verify(tradingStrategy, times(1)).priceUpdate(stock, limit);
    }
}
