package store.presentation.view.input;

import static store.presentation.view.Message.INPUT_PROMOTION_APPEND;

import store.presentation.view.input.abstractview.BooleanInputView;

public class PromotionAppendInputView extends BooleanInputView {

    @Override
    protected void printLabel(Object... args) {
        System.out.println(INPUT_PROMOTION_APPEND.get(args));
    }
}
