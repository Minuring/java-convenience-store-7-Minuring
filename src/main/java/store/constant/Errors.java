package store.constant;

import static store.constant.Constant.MAX_LENGTH_ITEM_NAME;
import static store.constant.Constant.MAX_STOCK_PRICE;
import static store.constant.Constant.MIN_LENGTH_ITEM_NAME;
import static store.constant.Constant.MIN_STOCK_PRICE;

public enum Errors {

    NOT_NEGATIVE_ID("아이디는 음수일 수 없습니다."),
    NOT_NEGATIVE_QUANTITY("수량은 음수일 수 없습니다."),
    LENGTH_STOCK_NAME("상품 이름은 %d자에서 %d자 사이여야 합니다.", MIN_LENGTH_ITEM_NAME, MAX_LENGTH_ITEM_NAME),
    LENGTH_STOCK_PRICE("상품 가격은 최소 %d원에서 %d원 사이여야 합니다.", MIN_STOCK_PRICE, MAX_STOCK_PRICE);

    private static final String PREFIX = "[ERROR]";
    private final String message;

    Errors(String messageFormat, Object... args) {
        this.message = PREFIX + String.format(messageFormat, args);
    }

    public String message() {
        return message;
    }
}
