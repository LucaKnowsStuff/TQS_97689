package tqs;
import  java.util.*;
public class StocksPortfolio {
    public String owner;
    private ArrayList<Stock> stocks =   new ArrayList<>();
    private IStockmarketService marketService;

    public IStockmarketService getStockService() {
        return marketService;
    }


    public void setOwner(String owner) {
        this.owner = owner;
    }
    public void setStockService(IStockmarketService marketService) {
        this.marketService = marketService;
    }


    public List<Stock> getStocks() {
        return stocks;
    }



    public void addStock( Stock stock) {
        stocks.add(stock);
    }


    public double getTotalValue(){
        double marketValue = 0.0;

        for(Stock stock:stocks){
            marketValue += this.marketService.lookUpPrice(stock.getLabel()) * stock.getQuantaty();
        }
        return marketValue;
    }

}
