package store.presentation.view.input;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static store.presentation.view.Message.INPUT_PROMOTION_FAILED;
import static store.presentation.view.ViewErrors.INVALID_FORMAT;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import store.presentation.view.IOTest;
import store.presentation.view.Message;

class PromotionFailedInputViewTest extends IOTest {

    @Test
    void 지정한_입력요구_문구를_띄운다() throws Exception {
        systemIn("Y");
        PromotionFailedInputView promotionFailedInputView = new PromotionFailedInputView();

        promotionFailedInputView.read("감자칩", 1);

        assertThat(getOutput()).isEqualTo(INPUT_PROMOTION_FAILED.get("감자칩", 1) + System.lineSeparator());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Y", "y", " y "})
    void Y또는_y를_입력하면_true이다(String input) throws Exception {
        systemIn(input);
        PromotionFailedInputView promotionFailedInputView = new PromotionFailedInputView();

        Boolean result = promotionFailedInputView.read("감자칩", 1);

        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"N", "n", " n "})
    void N또는_n을_입력하면_false이다(String input) throws Exception {
        systemIn(input);
        PromotionFailedInputView promotionFailedInputView = new PromotionFailedInputView();

        Boolean result = promotionFailedInputView.read("감자칩", 1);

        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"A", "\b", "\n", " ", "1"})
    void 형식에_안맞는_입력이_오면_예외가_발생한다(String strangeInput) throws Exception {
        systemIn(strangeInput);
        PromotionFailedInputView promotionFailedInputView = new PromotionFailedInputView();

        assertThatThrownBy(() -> promotionFailedInputView.read("감자칩", 1))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(INVALID_FORMAT.message());
    }
}