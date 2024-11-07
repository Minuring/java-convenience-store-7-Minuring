package store.presentation.view.input;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static store.presentation.view.ViewErrors.INVALID_FORMAT;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import store.presentation.view.IOTest;

class PromotionAppendInputViewTest extends IOTest {

    @ParameterizedTest
    @ValueSource(strings = {"Y", "y", " y "})
    void Y또는_y를_입력하면_true이다(String input) throws Exception {
        systemIn(input);
        PromotionAppendInputView promotionAppendInputView = new PromotionAppendInputView();

        Boolean result = promotionAppendInputView.read("감자칩", 1);

        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"N", "n", " n "})
    void N또는_n을_입력하면_false이다(String input) throws Exception {
        systemIn(input);
        PromotionAppendInputView promotionAppendInputView = new PromotionAppendInputView();

        Boolean result = promotionAppendInputView.read("감자칩", 1);

        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"A", "\b", "\n", " ", "1"})
    void 형식에_안맞는_입력이_오면_예외가_발생한다(String strangeInput) throws Exception {
        systemIn(strangeInput);
        PromotionAppendInputView promotionAppendInputView = new PromotionAppendInputView();

        assertThatThrownBy(() -> promotionAppendInputView.read("감자칩", 1))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(INVALID_FORMAT.message());
    }
}