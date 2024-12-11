package store.loader;

import store.StoreException;

public class InvalidFileFormatException extends StoreException {

    public InvalidFileFormatException() {
        super("잘못된 형식의 파일 입력입니다.");
    }
}
