package store.presentation.view;

public enum ViewErrors {
    INVALID_FORMAT("올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");

    private static final String PREFIX = "[ERROR]";
    private final String message;

    ViewErrors(String message) {
        this.message = message;
    }

    public String message() {
        return PREFIX + " " + message;
    }
}
