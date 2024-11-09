package store.item.domain;

import java.time.LocalDateTime;
import store.discount.promotion.domain.Promotion;

public class PromotionItem extends Item {

    private final Promotion promotion;

    public PromotionItem(String name, int price, int stock, Promotion promotion) {
        super(name, price, stock);
        this.promotion = promotion;
    }

    public int countFreeOnTake(int amount) {
        return amount / promotion.getUnit();
    }

    public int countTakeToGetFree(int freeAmount) {
        return freeAmount * promotion.getUnit();
    }

    public int countRemainsToApply(int amount) {
        int unit = promotion.getUnit();
        return (unit - amount % unit) % unit;
    }

    public boolean canApplyAt(LocalDateTime time) {
        return promotion.isInTime(time);
    }

    @Override
    public String toString() {
        return super.toString() + " " + promotion.getName();
    }
}
