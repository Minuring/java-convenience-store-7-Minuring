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

    public List<Product> findByName(String name) {
        return products.stream()
            .filter(product -> product.getName().equals(name))
            .toList();
    }

    public int countProducts() {
        return products.size();
    }
}
