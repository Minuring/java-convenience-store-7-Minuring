package store.mock;

import store.discount.promotion.domain.Promotion;
import store.discount.promotion.domain.Promotions;

public class MockPromotions implements Promotions {

    @Override
    public void addPromotion(Promotion promotion) {
        //do Nothing
    }

    @Override
    public Promotion getByName(String name) {
        return null;
    }
}
