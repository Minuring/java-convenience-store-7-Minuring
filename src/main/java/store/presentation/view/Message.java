package store.presentation.view;

public enum Message {
    WELCOME("안녕하세요. W편의점입니다."),
    PRODUCTS_HEADER("현재 보유하고 있는 상품입니다."),
    PRODUCT("- %s %,d원 %d개 %s"),
    PRODUCT_NO_STOCK("- %s %,d원 재고 없음 %s"),

    INPUT_PRODUCTS("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])"),
    INPUT_PROMOTION_APPEND("현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)"),
    INPUT_PROMOTION_FAILED("현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)"),
    INPUT_MEMBERSHIP("멤버십 할인을 받으시겠습니까? (Y/N)"),
    INPUT_BUY_MORE("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)"),

    BILL_HEADER("===========W 편의점============="),
    BILL_PRODUCTS_HEADER("상품명\t\t수량\t금액"),
    BILL_PRODUCT("%s \t\t%d \t%,d\n"),
    BILL_PROMOTION_HEADER("===========증\t정============="),
    BILL_PROMOTION("%s\t\t%d"),
    BILL_SEPARATOR("=============================="),
    BILL_TOTAL_PRICE("총구매액\t\t%d\t%,d"),
    BILL_PROMOTION_DISCOUNT("행사할인\t\t\t-%,d"),
    BILL_MEMBERSHIP_DISCOUNT("멤버십할인\t\t\t-%,d"),
    BILL_PAY("내실돈\t\t\t %,d");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String get(Object... args) {
        return String.format(message, args);
    }
}