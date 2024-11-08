package store.item.exception;

import static store.constant.Errors.NOT_FOUND_ITEM;

public class ItemNotFoundException extends IllegalArgumentException {

    public ItemNotFoundException() {
        super(NOT_FOUND_ITEM.message());
    }

    public ItemNotFoundException(Throwable cause) {
        super(NOT_FOUND_ITEM.message(), cause);
    }
}
