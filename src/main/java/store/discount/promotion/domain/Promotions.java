package store.discount.promotion.domain;

public interface Promotions {

    void addPromotion(Promotion promotion);

    Promotion getByName(String name);
}
