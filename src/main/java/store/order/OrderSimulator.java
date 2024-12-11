package store.order;

import java.time.LocalDate;
import java.util.List;
import store.order.OrderCart.OrderProduct;
import store.product.Product;
import store.product.Products;
import store.promotion.Promotion;
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

    public boolean canGetFreeIfAddOne(OrderProduct orderProduct) {
        String name = orderProduct.name();
        if (!products.hasPromotionItemByName(name)) {
            return false;
        }
        Product product = products.getPromotionProductByName(name);
        Promotion promotion = product.getPromotion().get();
        if (!promotion.isApplicable(LocalDate.now())) {
            return false;
        }

        return canGetFreeIfAddOne(orderProduct, product, promotion);
    }

    private boolean canGetFreeIfAddOne(OrderProduct orderProduct, Product product,
        Promotion promotion) {
        int quantity = orderProduct.quantity();
        int stock = product.getStock();
        int free = promotion.countFree(quantity, stock);
        int whenAdd = promotion.countFree(quantity + 1, stock);
        return free < whenAdd;
    }
}
