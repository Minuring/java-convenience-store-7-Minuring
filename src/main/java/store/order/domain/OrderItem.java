package store.order.domain;

public class OrderItem {

    private final String name;
    private final int price;
    private final int quantity;
    private final int free;

    public OrderItem(String name, int price, int quantity, int free) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.free = free;
    }

    public int getFree() {
        return free;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean hasFree() {
        return free > 0;
    }

    public int getTotalPrice() {
        return price * quantity;
    }

    public int getPromotionDiscountPrice() {
        return price * free;
    }
}
