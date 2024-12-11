package store.loader;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import store.promotion.Promotion;

class PromotionsLoaderTest {

    PromotionsLoader loader = new PromotionsLoader("src/main/resources/promotions.md");

    @Test
    void readPromotions() throws IOException {
        List<Promotion> promotions = loader.read();
        assertThat(promotions.size()).isEqualTo(3);
    }
}