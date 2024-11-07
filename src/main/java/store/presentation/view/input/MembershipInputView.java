package store.presentation.view.input;

import static store.presentation.view.Message.INPUT_MEMBERSHIP;

import store.presentation.view.input.abstractview.BooleanInputView;

public class MembershipInputView extends BooleanInputView {

    @Override
    protected void printLabel(Object... args) {
        System.out.println(INPUT_MEMBERSHIP.get(args));
    }
}
