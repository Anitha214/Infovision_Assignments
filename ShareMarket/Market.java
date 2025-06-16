package ShareMarket;

import java.util.*;

class Market {
    private final Map<String, Stock> stocks = new HashMap<>();

    public synchronized void addStock(Stock stock) {
        stocks.put(stock.getStockId(), stock);
    }

    public synchronized Stock getStock(String stockId) throws StockNotFoundException {
        if (!stocks.containsKey(stockId)) {
            throw new StockNotFoundException("Stock ID " + stockId + " not found.");
        }
        return stocks.get(stockId);
    }

    public Collection<Stock> getAllStocks() {
        return stocks.values();
    }
}
