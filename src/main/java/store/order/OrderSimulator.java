package store.order;

import java.util.List;
import store.order.OrderCart.OrderProduct;
import store.product.Products;
import store.promotion.Promotions;

public class OrderSimulator {

    private final Products products;
    private final Promotions promotions;

    public OrderSimulator(Products products, Promotions promotions) {
        this.products = products;
        this.promotions = promotions;
    }

    public boolean hasAllEnoughStock(OrderCart orderCart) {
        List<OrderProduct> orderProducts = orderCart.getProducts();
        return orderProducts.stream()
            .allMatch(orderProduct ->
                products.getTotalStock(orderProduct.name()) >= orderProduct.quantity()
            );
    }
}
