package store.constant;

import static store.constant.ConstantNumbers.EXACT_GET_PROMOTION;
import static store.constant.ConstantNumbers.MAX_BUY_PROMOTION;
import static store.constant.ConstantNumbers.MAX_LENGTH_ITEM_NAME;
import static store.constant.ConstantNumbers.MAX_LENGTH_PROMOTION_NAME;
import static store.constant.ConstantNumbers.MAX_STOCK_PRICE;
import static store.constant.ConstantNumbers.MIN_BUY_PROMOTION;
import static store.constant.ConstantNumbers.MIN_LENGTH_ITEM_NAME;
import static store.constant.ConstantNumbers.MIN_LENGTH_PROMOTION_NAME;
import static store.constant.ConstantNumbers.MIN_STOCK_PRICE;

public enum Errors {

    NOT_NEGATIVE_QUANTITY("수량은 음수일 수 없습니다."),
    LENGTH_STOCK_NAME("상품 이름은 %d자에서 %d자 사이여야 합니다.", MIN_LENGTH_ITEM_NAME, MAX_LENGTH_ITEM_NAME),
    LENGTH_STOCK_PRICE("상품 가격은 최소 %d원에서 %d원 사이여야 합니다.", MIN_STOCK_PRICE, MAX_STOCK_PRICE),
    LENGTH_PROMOTION_NAME("프로모션 이름은 %d자에서 %d자 사이여야 합니다.", MIN_LENGTH_PROMOTION_NAME,
        MAX_LENGTH_PROMOTION_NAME),
    LENGTH_PROMOTION_BUY("프로모션 혜택은 최소 %d개에서 최대 %d까지 구매해야 합니다.", MIN_BUY_PROMOTION,
        MAX_BUY_PROMOTION),
    EXACT_PROMOTION_GET("프로모션 혜택 당 증정상품은 반드시 %d개입니다.", EXACT_GET_PROMOTION),
    NOT_ENOUGH_STOCK("재고가 충분하지 않습니다."),
    NOT_FOUND_PROMOTION("존재하지 않는 프로모션입니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    Errors(String messageFormat, Object... args) {
        this.message = PREFIX + String.format(messageFormat, args);
    }

    public String message() {
        return message;
    }
}
