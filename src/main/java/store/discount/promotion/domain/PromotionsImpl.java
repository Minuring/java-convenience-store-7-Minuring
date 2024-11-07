package store.discount.promotion.domain;

import static store.constant.Errors.NOT_FOUND_PROMOTION;

import java.util.ArrayList;
import java.util.List;
import store.discount.promotion.exception.PromotionNotFoundException;

public class PromotionsImpl implements Promotions {

    private final List<Promotion> promotions = new ArrayList<>();

    public void addPromotion(Promotion promotion) {
        promotions.add(promotion);
    }

    public Promotion getByName(String name) {
        for (Promotion promotion : promotions) {
            if (promotion.getName().equals(name)) {
                return promotion;
            }
        }
        throw new PromotionNotFoundException(NOT_FOUND_PROMOTION.message());
    }
}
