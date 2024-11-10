package store.constant;

public enum ConstantNumbers {
    MIN_STOCK_PRICE(100),
    MAX_STOCK_PRICE(100_000),
    MIN_LENGTH_ITEM_NAME(2),
    MAX_LENGTH_ITEM_NAME(10),
    MIN_LENGTH_PROMOTION_NAME(4),
    MAX_LENGTH_PROMOTION_NAME(12),
    MIN_BUY_PROMOTION(1),
    MAX_BUY_PROMOTION(4),
    EXACT_GET_PROMOTION(1),
    PERCENT_MEMBERSHIP(30),
    MAX_MEMBERSHIP(8000);

    private final int value;

    ConstantNumbers(int value) {
        this.value = value;
    }

    public int get() {
        return value;
    }
}
