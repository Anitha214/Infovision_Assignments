package ShareMarket;

public class ShareMarketTradingSystem {
    public static void main(String[] args) throws InterruptedException {
        Market market = new Market();

    
        market.addStock(new Stock("AAPL", "Apple", 150, 100));
        market.addStock(new Stock("GOOG", "Google", 1200, 100));
        market.addStock(new Stock("TSLA", "Tesla", 700, 100));

        
        StockPriceUpdater priceUpdater = new StockPriceUpdater(market);
        priceUpdater.start();


        Trader t1 = new Trader("T1", "Anitha", market, Trader.Strategy.CAUTIOUS);
        Trader t2 = new Trader("T2", "Abitha", market, Trader.Strategy.AGGRESSIVE);
        Trader t3 = new Trader("T3", "Sophitha", market, Trader.Strategy.BALANCED);

        t1.start();
        t2.start();
        t3.start();

      
        t1.join();
        t2.join();
        t3.join();

      
        priceUpdater.stopUpdater();

        System.out.println("\n--- Final Trader Portfolios ---");
        t1.printPortfolio();
        t2.printPortfolio();
        t3.printPortfolio();
    }
}
