package store.product;

import store.StoreException;

public class InvalidPriceException extends StoreException {

    public InvalidPriceException() {
        super("가격은 0이하일 수 없습니다.");
    }
}
