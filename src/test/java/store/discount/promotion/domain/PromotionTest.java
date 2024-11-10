package store.discount.promotion.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static store.constant.Errors.EXACT_PROMOTION_GET;
import static store.constant.Errors.LENGTH_PROMOTION_BUY;
import static store.constant.Errors.LENGTH_PROMOTION_NAME;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class PromotionTest {

    @DisplayName("날짜가 프로모션 기간에 속하는 지 판단할 수 있다.")
    @ParameterizedTest
    @MethodSource("provideTimes")
    void isInTime(LocalDateTime time, boolean expected) {
        LocalDate from = LocalDate.of(2024, 11, 5);
        LocalDate to = LocalDate.of(2024, 11, 10);

        Promotion promotion = new Promotion("ABCD", 2, 1, from, to);

        boolean inTime = promotion.isInTime(time);

        assertThat(inTime).isEqualTo(expected);
    }

    private static Stream<Arguments> provideTimes() {
        return Stream.of(Arguments.of(LocalDateTime.of(2024, 11, 5, 0, 0), true),
            Arguments.of(LocalDateTime.of(2024, 11, 10, 23, 59), true),
            Arguments.of(LocalDateTime.of(2024, 11, 4, 23, 59), false),
            Arguments.of(LocalDateTime.of(2024, 11, 11, 0, 0), false));
    }

    @DisplayName("프로모션의 이름이 4자이상 12자이하이면 성공한다.")
    @Test
    void 프로모션_이름은_4자이상_12자이하이다() throws Exception {
        assertDoesNotThrow(() -> new Promotion("ABCD", 2, 1, LocalDate.now(), LocalDate.now()));
    }

    @DisplayName("프로모션의 이름이 4자 미만이거나 12자 초과이면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"ABC", "ABCDEABCDEABC"})
    void 범위_밖의_프로모션_이름(String name) throws Exception {
        assertThatThrownBy(() -> new Promotion(name, 2, 1, LocalDate.now(), LocalDate.now()))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(LENGTH_PROMOTION_NAME.message());
    }

    @DisplayName("프로모션의 Buy가 1이상 4이하이면 성공한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    void 프로모션_Buy_1부터_4(int buy) throws Exception {
        assertDoesNotThrow(() -> new Promotion("ABCD", buy, 1, LocalDate.now(), LocalDate.now()));
    }

    @DisplayName("프로모션의 Buy가 1미만 혹은 4초과이면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 5})
    void 프로모션_Buy_1미만_4초과(int buy) throws Exception {
        assertThatThrownBy(() -> new Promotion("ABCD", buy, 1, LocalDate.now(), LocalDate.now()))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(LENGTH_PROMOTION_BUY.message());
    }

    @DisplayName("프로모션의 Get은 1이어야한다.")
    @Test
    void 프로모션_Get_반드시_1() throws Exception {
        assertDoesNotThrow(() -> new Promotion("ABCD", 2, 1, LocalDate.now(), LocalDate.now()));
    }

    @DisplayName("프로모션의 Get이 1이 아니면 실패한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 2, 3})
    void 프로모션_Get_1_아닐때(int get) throws Exception {
        assertThatThrownBy(() -> new Promotion("ABCD", 2, get, LocalDate.now(), LocalDate.now()))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(EXACT_PROMOTION_GET.message());
    }
}