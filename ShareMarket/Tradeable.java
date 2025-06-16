package ShareMarket;

interface Tradeable {
    void buyStock(String stockId, int qty) throws Exception;
    void sellStock(String stockId, int qty) throws Exception;
}
