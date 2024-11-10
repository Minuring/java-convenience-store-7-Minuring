package store.presentation.view.input;

import static org.assertj.core.api.Assertions.assertThat;
import static store.presentation.view.Message.INPUT_PROMOTION_FAILED;

import org.junit.jupiter.api.Test;
import store.presentation.view.IOTest;

class PromotionFailedInputViewTest extends IOTest {

    @Test
    void 지정한_입력요구_문구를_띄운다() throws Exception {
        systemIn("Y");
        PromotionFailedInputView promotionFailedInputView = new PromotionFailedInputView();

        promotionFailedInputView.read("감자칩", 1);

        assertThat(getOutput()).contains(
            INPUT_PROMOTION_FAILED.get("감자칩", 1) + System.lineSeparator());
    }
}
