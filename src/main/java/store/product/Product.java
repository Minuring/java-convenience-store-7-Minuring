package store.product;

import java.util.Optional;
import store.promotion.Promotion;

public class Product {

    private final String name;
    private final int price;
    private final int stock;
    private final Optional<Promotion> promotion;

    private Product(String name, int price, int stock, Promotion promotion) {
        validatePrice(price);
        validateStock(stock);
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.promotion = Optional.ofNullable(promotion);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public boolean isPromotion() {
        return promotion.isPresent();
    }

    public static Product of(String name, int price, int stock) {
        return new Product(name, price, stock, null);
    }

    public static Product of(String name, int price, int stock, Promotion promotion) {
        return new Product(name, price, stock, promotion);
    }

    private void validatePrice(int price) {
        if (price <= 0) {
            throw new InvalidPriceException();
        }
    }

    private void validateStock(int stock) {
        if (stock < 0) {
            throw new InvalidStockException();
        }
    }
}
