package ShareMarket;

public class Stock {
    private final String stockId;
    private final String stockName;
    private double pricePerShare;
    private int availableShares;

    public Stock(String stockId, String stockName, double pricePerShare, int availableShares) {
        this.stockId = stockId;
        this.stockName = stockName;
        this.pricePerShare = pricePerShare;
        this.availableShares = availableShares;
    }

    // synchronized getters/setters for thread safety

    public synchronized double getPricePerShare() {
        return pricePerShare;
    }

    public synchronized void updatePrice() {
        double change = (Math.random() * 10) - 5; // fluctuate ±5
        pricePerShare = Math.max(1, pricePerShare + change);
        System.out.println(stockName + " new price: " + String.format("%.2f", pricePerShare));
    }

    public synchronized void addShares(int qty) {
        this.availableShares += qty;
    }

    public synchronized void removeShares(int qty) throws InsufficientMarketSharesException {
        if (qty > this.availableShares) {
            throw new InsufficientMarketSharesException("Not enough shares in the market.");
        }
        this.availableShares -= qty;
    }

    public String getStockId() {
        return stockId;
    }

    public String getStockName() {
        return stockName;
    }
    public int getAvailableShares() {
        return availableShares;
    }
}



