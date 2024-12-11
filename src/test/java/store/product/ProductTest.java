package store.product;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    void createProductPriceLessThanOrEqualToZero() {
        assertThatThrownBy(() -> Product.of("A", 0, 1))
            .isInstanceOf(InvalidPriceException.class);
    }

    @Test
    void createProductStockLessThanZero() {
        assertThatThrownBy(() -> Product.of("A", 1000, -1))
            .isInstanceOf(InvalidStockException.class);
    }
}