package ShareMarket;

import java.util.*;

class Trader extends Thread implements Tradeable {
    private final String traderId;
    private final String name;
    private final Market market;
    private final Map<String, Integer> portfolio = new HashMap<>();
    private final Strategy strategy;

    // Add enum for strategies
    public enum Strategy { AGGRESSIVE, CAUTIOUS, BALANCED }

    // Modify constructor to accept Strategy
    public Trader(String traderId, String name, Market market, Strategy strategy) {
        this.traderId = traderId;
        this.name = name;
        this.market = market;
        this.strategy = strategy;
    }

    @Override
    public void run() {
        String[] stockIds = {"AAPL", "GOOG", "TSLA"};
        Random rand = new Random();

        // Guaranteed at least one buy
        try {
            String stockId = stockIds[rand.nextInt(stockIds.length)];
            int qty = rand.nextInt(3) + 1;
            buyStock(stockId, qty);
        } catch (Exception e) {
            System.err.println("[" + name + "] " + e.getMessage());
        }

        // 4 random buy/sell operations with strategy logic
        for (int i = 0; i < 4; i++) {
            String stockId = stockIds[rand.nextInt(stockIds.length)];
            int qty = rand.nextInt(3) + 1;

            try {
                Stock stock = market.getStock(stockId);
                double currentPrice = stock.getPricePerShare();

                // Strategy logic: Cautious traders avoid stocks priced above 100
                if (strategy == Strategy.CAUTIOUS && currentPrice > 100) {
                    // Skip this stock to buy/sell, continue loop
                    continue;
                }

                if (rand.nextBoolean()) {
                    buyStock(stockId, qty);
                } else {
                    sellStock(stockId, qty);
                }
            } catch (Exception e) {
                System.err.println("[" + name + "] " + e.getMessage());
            }
        }
    }

    @Override
    public synchronized void buyStock(String stockId, int qty) throws Exception {
        Stock stock = market.getStock(stockId);
        synchronized (stock) {
            stock.removeShares(qty);
        }
        portfolio.put(stockId, portfolio.getOrDefault(stockId, 0) + qty);
        System.out.println(name + " bought " + qty + " shares of " + stockId);
    }

    @Override
    public synchronized void sellStock(String stockId, int qty) throws Exception {
        if (!portfolio.containsKey(stockId) || portfolio.get(stockId) < qty) {
            throw new InsufficientSharesException("Cannot sell more shares than owned.");
        }

        Stock stock = market.getStock(stockId);
        synchronized (stock) {
            stock.addShares(qty);
        }
        portfolio.put(stockId, portfolio.get(stockId) - qty);
        System.out.println(name + " sold " + qty + " shares of " + stockId);
    }

    public void printPortfolio() {
        System.out.println("Portfolio of " + name + ": " + portfolio);
    }

    public String getTraderName() {
        return name;
    }


    public Map<String, Integer> getPortfolio() {
        return portfolio;
    }
}
