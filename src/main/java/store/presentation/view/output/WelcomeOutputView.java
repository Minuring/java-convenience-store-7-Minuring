package store.presentation.view.output;

import static store.presentation.view.Message.WELCOME;

import store.presentation.view.output.abstractview.NoArgumentOutputView;

public class WelcomeOutputView extends NoArgumentOutputView {

    @Override
    protected void printBody() {
        System.out.println(WELCOME.get());
    }
}
