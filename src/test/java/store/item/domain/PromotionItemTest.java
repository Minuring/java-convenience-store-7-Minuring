package store.item.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import store.discount.promotion.domain.Promotion;

class PromotionItemTest {

    private Promotion promotion = new Promotion("프로모션A", 2, 1,
        LocalDate.of(2001, 1, 1), LocalDate.of(2025, 12, 31));
    private PromotionItem promotionItem = new PromotionItem("ABCD", 1000, 10, promotion);

    @DisplayName("수량에 대해 몇 개를 증정받을 수 있는지 계산한다.")
    @ParameterizedTest
    @MethodSource("provideAmount")
    void countFreeOnTake(int amount, int expectedFreeCount) {
        assertThat(promotionItem.countFreeOnTake(amount)).isEqualTo(expectedFreeCount);
    }

    private static Stream<Arguments> provideAmount() {
        return Stream.of(Arguments.of(1, 0),
            Arguments.of(2, 0),
            Arguments.of(3, 1),
            Arguments.of(7, 2)
        );
    }

    @DisplayName("현재 수량에 대해 프로모션 증정을 받기 위해 추가해야하는 수량을 계산한다.")
    @ParameterizedTest
    @MethodSource("provideCurrentAmount")
    void countRemainsToApply(int currentAmount, int expectedRemainCount) {
        assertThat(promotionItem.countRemainsToApply(currentAmount)).isEqualTo(expectedRemainCount);
    }

    private static Stream<Arguments> provideCurrentAmount() {
        return Stream.of(Arguments.of(1, 2),
            Arguments.of(2, 1),
            Arguments.of(3, 0),
            Arguments.of(7, 2),
            Arguments.of(8, 1),
            Arguments.of(9, 0)
        );
    }
}