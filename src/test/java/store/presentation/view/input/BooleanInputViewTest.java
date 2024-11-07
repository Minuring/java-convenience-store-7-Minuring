package store.presentation.view.input;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static store.presentation.view.ViewErrors.INVALID_FORMAT;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import store.presentation.view.IOTest;
import store.presentation.view.input.abstractview.BooleanInputView;

public class BooleanInputViewTest extends IOTest {

    private final BooleanInputView mockView = new BooleanInputView() {
        @Override
        protected void printLabel(Object... args) {
            //do Nothing
        }
    };


    @ParameterizedTest
    @ValueSource(strings = {"Y", "y", " y "})
    void Y또는_y를_입력하면_true이다(String input) throws Exception {
        systemIn(input);

        Boolean result = mockView.read();

        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"N", "n", " n "})
    void N또는_n을_입력하면_false이다(String input) throws Exception {
        systemIn(input);

        Boolean result = mockView.read();

        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"A", "\b", "\n", " ", "1"})
    void 형식에_안맞는_입력이_오면_예외가_발생한다(String strangeInput) throws Exception {
        systemIn(strangeInput);

        assertThatThrownBy(mockView::read)
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(INVALID_FORMAT.message());
    }
}
