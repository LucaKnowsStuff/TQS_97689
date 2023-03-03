package tqs;


import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Mock
    IStockmarketService marketService;
    @InjectMocks
    StocksPortfolio portfolio;

    @BeforeEach
    void setOwner() {
        portfolio.setOwner("Unsofisticated Investments");
    }

    @Test
    public void getTotalValueTest() {
        portfolio.setOwner("Unsofisticated Investments");
        portfolio.addStock(new Stock("Tesla" , 10));
        portfolio.addStock(new Stock("Zoom" , 100));
        portfolio.addStock(new Stock("Twitter" , 1000));

        when(marketService.lookUpPrice("Tesla")).thenReturn(2.3);
        when(marketService.lookUpPrice("Zoom")).thenReturn(1.9);
        when(marketService.lookUpPrice("Twitter")).thenReturn(0.7);

        double testResult = portfolio.getTotalValue();

        //assertEquals(23 + 190 + 700 , testResult);
        assertThat(testResult,is((double) 23 + 190 + 700));
        verify(marketService, times(3)).lookUpPrice(anyString());

    }
}
