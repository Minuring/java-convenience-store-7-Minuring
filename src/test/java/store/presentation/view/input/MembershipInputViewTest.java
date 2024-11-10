package store.presentation.view.input;

import static org.assertj.core.api.Assertions.assertThat;
import static store.presentation.view.Message.INPUT_MEMBERSHIP;

import org.junit.jupiter.api.Test;
import store.presentation.view.IOTest;

class MembershipInputViewTest extends IOTest {

    @Test
    void 지정한_입력요구_문구를_띄운다() throws Exception {
        systemIn("Y");
        MembershipInputView MembershipInputView = new MembershipInputView();

        MembershipInputView.read();

        assertThat(getOutput()).contains(INPUT_MEMBERSHIP.get());
    }
}
