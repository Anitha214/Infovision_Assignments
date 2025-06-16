package ShareMarket;

class InsufficientMarketSharesException extends Exception {
    public InsufficientMarketSharesException(String message) {
        super(message);
    }
}