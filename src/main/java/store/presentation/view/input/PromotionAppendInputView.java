package store.presentation.view.input;

import static store.presentation.view.Message.INPUT_PROMOTION_APPEND;

import store.order.service.RequiredConfirmListener;
import store.presentation.view.input.abstractview.BooleanInputView;

public class PromotionAppendInputView extends BooleanInputView implements
    RequiredConfirmListener<String, Integer> {

    @Override
    protected void printLabel(Object... args) {
        System.out.println(INPUT_PROMOTION_APPEND.get(args));
    }

    @Override
    public Boolean apply(String itemName, Integer amount) {
        return super.read(itemName, amount);
    }
}
