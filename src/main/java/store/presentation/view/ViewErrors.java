package store.presentation.view;

public enum ViewErrors {
    INVALID_FORMAT("올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요."),
    INVALID_INPUT("잘못된 입력입니다. 다시 입력해 주세요."),
    DUPLICATED_ITEMS("하나의 상품은 한 번에 걸쳐 구매 가능합니다. 다시 입력해 주세요.");

    private static final String PREFIX = "[ERROR]";
    private final String message;

    ViewErrors(String message) {
        this.message = message;
    }

    public String message() {
        return PREFIX + " " + message;
    }
}
