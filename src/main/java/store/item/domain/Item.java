package store.item.domain;

public abstract class Item {

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
}
