package store.item.domain;

import store.discount.promotion.domain.Promotion;

public class PromotionItem extends Item {

    private final Promotion promotion;

    public PromotionItem(String name, int price, int stock, Promotion promotion) {
        super(name, price, stock);
        this.promotion = promotion;
    }

    @Override
    public String toString() {
        return super.toString() + " " + name;
    }
}
