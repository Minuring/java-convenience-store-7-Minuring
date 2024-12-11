package store.order;

import java.util.ArrayList;
import java.util.List;

public class OrderCart {

    public record OrderProduct(String name, int quantity) {
    }

    private final List<OrderProduct> orderProducts = new ArrayList<>();

    public void addProduct(OrderProduct orderProduct) {
        orderProducts.add(orderProduct);
    }

    public List<OrderProduct> getProducts() {
        return orderProducts;
    }
}
