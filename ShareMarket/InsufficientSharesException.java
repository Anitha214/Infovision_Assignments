package ShareMarket;

class InsufficientSharesException extends Exception {
    public InsufficientSharesException(String message) {
        super(message);
    }
}
