package store;

import java.util.Collection;
import java.util.List;
import store.product.Product;
import store.product.Products;
import store.promotion.Promotion;
import store.promotion.Promotions;

public class Store {

    private final Promotions promotions;
    private final Products products;

    public Store(List<Promotion> promotions, List<Product> products) {
        this.promotions = new Promotions(promotions);
        this.products = new Products(products);
        addAllProductsThatOnlyPromotionProduct(products);
    }

    public Products getProducts() {
        return products;
    }

    private void addAllProductsThatOnlyPromotionProduct(List<Product> products) {
        List<Product> onlyPromotionProducts = products.stream()
            .filter(Product::isPromotion)
            .map(Product::getName)
            .map(this.products::findByName)
            .filter(list -> list.size() == 1)
            .flatMap(Collection::stream)
            .toList();
        onlyPromotionProducts.forEach(this.products::addProduct);
    }
}
