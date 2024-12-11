package store.promotion;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PromotionTest {

    LocalDate mockDate1 = LocalDate.of(2020, 1, 1);
    LocalDate mockDate2 = LocalDate.of(2020, 2, 1);

    @DisplayName("프로모션 Get이 1이 아니면 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {0, 2})
    void createPromotionGetIsNotOne(int get) {
        assertThatThrownBy(() -> Promotion.of("A", 1, get, mockDate1, mockDate2))
            .isInstanceOf(UnsupportedPromotionGetException.class);
    }

    @DisplayName("프로모션 Buy가 1보다 작으면 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void createPromotionBuyLessThanOne(int buy) {
        assertThatThrownBy(() -> Promotion.of("A", buy, 1, mockDate1, mockDate2))
            .isInstanceOf(UnsupportedPromotionGetException.class);
    }
}