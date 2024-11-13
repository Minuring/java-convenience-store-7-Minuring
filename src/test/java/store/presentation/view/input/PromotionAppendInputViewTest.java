package store.presentation.view.input;

import static org.assertj.core.api.Assertions.assertThat;
import static store.presentation.view.Message.INPUT_PROMOTION_APPEND;

import org.junit.jupiter.api.Test;
import store.presentation.view.IOTest;

class PromotionAppendInputViewTest extends IOTest {

    @Test
    void 지정한_입력요구_문구를_띄운다() throws Exception {
        systemIn("Y");
        PromotionAppendInputView promotionAppendInputView = new PromotionAppendInputView();

        promotionAppendInputView.read("감자칩", 1);

        assertThat(getOutput()).contains(
            INPUT_PROMOTION_APPEND.get("감자칩", 1));
    }
}
