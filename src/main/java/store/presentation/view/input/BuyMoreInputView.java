package store.presentation.view.input;

import static store.presentation.view.Message.INPUT_BUY_MORE;

import store.presentation.view.input.abstractview.BooleanInputView;

public class BuyMoreInputView extends BooleanInputView {

    @Override
    protected void printLabel(Object... args) {
        System.out.println(INPUT_BUY_MORE.get());
    }
}
