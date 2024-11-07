package store.discount.promotion;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.discount.promotion.exception.PromotionNotFoundException;

class PromotionsTest {

    private final Promotions promotions = new Promotions();
    private Promotion samplePromotion;

    @BeforeEach
    void setUp() {
        samplePromotion = new Promotion("ABCD", 2, 1, now(), now());
        promotions.addPromotion(samplePromotion);
    }

    @Test
    void 프로모션_이름으로_프로모션을_가져올수있다() throws Exception {
        Promotion foundPromotion = promotions.getByName("ABCD");
        assertThat(foundPromotion).isEqualTo(samplePromotion);
    }

    @Test
    void 존재하지않는_이름의_프로모션으로_꺼내면_예외가_발생한다() throws Exception {
        assertThatThrownBy(() -> promotions.getByName("ABCDEF"))
            .isInstanceOf(PromotionNotFoundException.class);
    }
}