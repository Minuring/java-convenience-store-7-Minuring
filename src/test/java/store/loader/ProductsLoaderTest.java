package store.loader;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.product.Product;
import store.promotion.Promotion;

class ProductsLoaderTest {

    private List<Promotion> promotions;
    ProductsLoader productsLoader;

    @BeforeEach
    void setUp() throws IOException {
        PromotionsLoader promotionsLoader = new PromotionsLoader(
            "src/main/resources/promotions.md");
        this.promotions = promotionsLoader.read();
        this.productsLoader = new ProductsLoader("src/main/resources/products.md", promotions);
    }

    @Test
    void readPromotions() throws IOException {
        List<Product> products = productsLoader.read();
        assertThat(products.size()).isEqualTo(16);
    }
}