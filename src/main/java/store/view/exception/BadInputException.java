package store.view.exception;

public class BadInputException extends IllegalArgumentException {

    public BadInputException() {
        super("잘못된 입력입니다. 다시 입력해 주세요.");
    }
}
