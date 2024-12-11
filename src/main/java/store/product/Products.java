package store.product;

import java.util.List;

public class Products {

    private final List<Product> products;

    public Products(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public boolean hasPromotionItemByName(String name) {
        return findByName(name).stream().anyMatch(Product::isPromotion);
    }

    public Product getPromotionProductByName(String name) {
        return findByName(name).stream()
            .filter(Product::isPromotion)
            .findFirst()
            .orElseThrow(ProductNotFoundException::new);
    }

    public Product getNormalProductByName(String name) {
        return findByName(name).stream()
            .filter(product -> !product.isPromotion())
            .findFirst()
            .orElseThrow(ProductNotFoundException::new);
    }

    public List<Product> findByName(String name) {
        return products.stream()
            .filter(product -> product.getName().equals(name))
            .toList();
    }

    public int getTotalStock(String name) {
        return findByName(name).stream()
            .mapToInt(Product::getStock)
            .sum();
    }

    public int countProducts() {
        return products.size();
    }
}
