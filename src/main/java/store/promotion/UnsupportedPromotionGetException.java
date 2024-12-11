package store.promotion;

public class UnsupportedPromotionGetException extends RuntimeException {

    public UnsupportedPromotionGetException() {
        super("프로모션은 반드시 1개만 증정할 수 있습니다.");
    }
}
