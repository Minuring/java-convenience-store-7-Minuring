package store.order.domain;

public class OrderItem {

    private final String name;
    private final int price;
    private int quantity = 0;
    private int free = 0;

    public OrderItem(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public OrderItem(String name, int price, int quantity, int free) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.free = free;
    }

    public void setFree(int free) {
        this.free = free;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
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
