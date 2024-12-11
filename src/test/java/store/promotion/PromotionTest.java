package store.promotion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
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

    @DisplayName("구매수량과 재고수량을 고려해 증정받을 수 있는 상품의 개수 계산")
    @ParameterizedTest
    @CsvSource({
        "1,10,0", "2,10,0", "3,10,1",
        "3,0,0", "3,2,0", "3,3,1",
        "9,10,3", "10,10,3", "11,10,3", "12,10,3"
    })
    void countFreeWithStock(int buyAmount, int stock, int expectedFree) {
        Promotion promotion = Promotion.of("A", 2, 1, mockDate1, mockDate2);
        int free = promotion.countFreeWithStock(buyAmount, stock);
        assertThat(free).isEqualTo(expectedFree);
    }

    @ParameterizedTest
    @MethodSource("inRangeDates")
    void isApplicableInRangeDate(LocalDate date) {
        Promotion promotion = Promotion.of("A", 2, 1, mockDate1, mockDate2);
        boolean applicable = promotion.isApplicable(date);
        assertThat(applicable).isTrue();
    }

    static List<Arguments> inRangeDates() {
        return List.of(
            Arguments.of(LocalDate.of(2020, 1, 1)),
            Arguments.of(LocalDate.of(2020, 1, 15)),
            Arguments.of(LocalDate.of(2020, 2, 1))
        );
    }

    @ParameterizedTest
    @MethodSource("outOfRangeDates")
    void isApplicableOutOfRangeDate(LocalDate date) {
        Promotion promotion = Promotion.of("A", 2, 1, mockDate1, mockDate2);
        boolean applicable = promotion.isApplicable(date);
        assertThat(applicable).isFalse();
    }

    static List<Arguments> outOfRangeDates() {
        return List.of(
            Arguments.of(LocalDate.of(2020, 2, 2)),
            Arguments.of(LocalDate.of(2020, 12, 31)),
            Arguments.of(LocalDate.of(2020, 5, 5))
        );
    }
}