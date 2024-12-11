package store.loader;

public class InvalidFileFormatException extends RuntimeException {

    public InvalidFileFormatException() {
        super("잘못된 형식의 파일 입력입니다.");
    }
}
