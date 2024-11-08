package store.item.domain;

import java.util.Objects;

public abstract class Item implements Comparable<Item> {

    protected final String name;
    protected final int price;
    protected int stock;

    public Item(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    @Override
    public String toString() {
        if (stock > 0) {
            return String.format("%s %,d원 %d개", name, price, stock);
        }
        return String.format("%s %,d원 재고 없음", name, price);
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Item other) {
        if (comparingNormalPromotion(other)) {
            return 1;
        }
        if (comparingPromotionNormal(other)) {
            return -1;
        }
        return 0;
    }

    private boolean comparingPromotionNormal(Item other) {
        return Objects.equals(name, other.name)
            && this instanceof PromotionItem && other instanceof NormalItem;
    }

    private boolean comparingNormalPromotion(Item other) {
        return Objects.equals(name, other.name)
            && this instanceof NormalItem && other instanceof PromotionItem;
    }
}
