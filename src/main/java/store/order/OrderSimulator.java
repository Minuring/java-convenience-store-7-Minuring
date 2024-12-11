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
        if (isPromotionInconsiderable(orderProduct)) {
            return false;
        }

        Product product = products.getPromotionProductByName(orderProduct.name());
        Promotion promotion = product.getPromotion().get();
        return canGetFreeIfAddOne(orderProduct, product, promotion);
    }

    private boolean canGetFreeIfAddOne(OrderProduct orderProduct, Product product,
        Promotion promotion) {
        int quantity = orderProduct.quantity();
        int stock = product.getStock();
        int free = promotion.countFreeWithStock(quantity, stock);
        int whenAdd = promotion.countFreeWithStock(quantity + 1, stock);
        return free < whenAdd;
    }

    public int calculateNotApplicablePromotionPartly(OrderProduct orderProduct) {
        if (isPromotionInconsiderable(orderProduct)) {
            return 0; //부분적으로 정가결제 해야할 수량이 없음.
        }

        Product product = products.getPromotionProductByName(orderProduct.name());
        Promotion promotion = product.getPromotion().get();
        return calculatePartAmount(orderProduct, product, promotion);
    }

    private int calculatePartAmount(OrderProduct orderProduct, Product product, Promotion promotion) {
        int stock = product.getStock();
        int quantity = orderProduct.quantity();
        int freeWhenBuyInStock = promotion.countFreeWithStock(quantity, stock);
        int appliedQuantity = promotion.countAppliedQuantity(quantity, stock);
        if (quantity > stock && freeWhenBuyInStock >= 1) {
            return quantity - appliedQuantity;
        }
        return 0;
    }

    private boolean isPromotionInconsiderable(OrderProduct orderProduct) {
        String name = orderProduct.name();
        if (!products.hasPromotionItemByName(name)) {
            return true;
        }
        Product product = products.getPromotionProductByName(name);
        Promotion promotion = product.getPromotion().get();
        return !promotion.isApplicable(LocalDate.now());
    }
}
