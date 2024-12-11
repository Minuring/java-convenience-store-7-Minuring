package store.product;

public class NotEnoughStockException extends IllegalArgumentException {

    public NotEnoughStockException() {
        super("재고가 부족합니다.");
    }
}
