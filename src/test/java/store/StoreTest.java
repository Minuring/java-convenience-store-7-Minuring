package store;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.loader.ProductsLoader;
import store.loader.PromotionsLoader;
import store.product.Product;
import store.promotion.Promotion;

class StoreTest {

    private List<Promotion> promotions;
    private List<Product> products;

    @BeforeEach
    void setUp() throws IOException {
        promotions = new PromotionsLoader("src/main/resources/promotions.md").read();
        products = new ProductsLoader("src/main/resources/products.md", promotions).read();
    }

    @Test
    void addAllProductsThatOnlyPromotionProduct() {
        Store store = new Store(promotions, products);
        assertThat(store.getProducts().countProducts()).isEqualTo(18);
    }
}