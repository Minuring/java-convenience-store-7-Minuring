package store.promotion;

import store.StoreException;

public class PromotionNotFoundException extends StoreException {
  public PromotionNotFoundException() {
    super("존재하지 않는 프로모션입니다.");
  }
}
