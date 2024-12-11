package store.order;

import java.util.ArrayList;
import java.util.List;

public class OrderCart {

    public static class OrderProduct {

        private String name;
        private int quantity;

        public OrderProduct(String name, int quantity) {
            this.name = name;
            this.quantity = quantity;
        }

        public String name() {
            return name;
        }

        public int quantity() {
            return quantity;
        }

        public void increaseQuantity() {
            this.quantity += 1;
        }
    }

    private final List<OrderProduct> orderProducts = new ArrayList<>();

    public void addProduct(OrderProduct orderProduct) {
        orderProducts.add(orderProduct);
    }

    public List<OrderProduct> getProducts() {
        return orderProducts;
    }
}
