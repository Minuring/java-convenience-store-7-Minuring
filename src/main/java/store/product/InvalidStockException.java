package store.product;

import org.junit.jupiter.api.extension.ExtensionContext.Store;
import store.StoreException;

public class InvalidStockException extends StoreException {

    public InvalidStockException() {
        super("재고는 0보다 작을 수 없습니다.");
    }
}
