package store.presentation.view.output;

import static org.assertj.core.api.Assertions.assertThat;
import static store.presentation.view.Message.WELCOME;

import org.junit.jupiter.api.Test;
import store.presentation.view.IOTest;

class WelcomeOutputViewTest extends IOTest {

    @Test
    void 환영_문구를_보여준다() throws Exception {
        WelcomeOutputView welcomeOutputView = new WelcomeOutputView();

        welcomeOutputView.print();

        assertThat(getOutput()).contains(WELCOME.get());
    }
}