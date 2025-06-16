package ShareMarket;

public class StockPriceUpdater extends Thread {
    private final Market market;
    private volatile boolean running = true;

    public StockPriceUpdater(Market market) {
        this.market = market;
    }

    public void run() {
        while (running) {
            for (Stock stock : market.getAllStocks()) {
                stock.updatePrice();
            }
            try {
                Thread.sleep(5000);  
            } catch (InterruptedException e) {
                running = false;
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stopUpdater() {
        running = false;
        this.interrupt();
    }
}
