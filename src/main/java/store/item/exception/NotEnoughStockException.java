package store.item.exception;

import static store.constant.Errors.NOT_ENOUGH_STOCK;

public class NotEnoughStockException extends RuntimeException {

    public NotEnoughStockException() {
        super(NOT_ENOUGH_STOCK.message());
    }

    public NotEnoughStockException(Throwable cause) {
        super(NOT_ENOUGH_STOCK.message(), cause);
    }
}
