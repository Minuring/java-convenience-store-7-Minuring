package store.presentation.view.input;

import static store.presentation.view.Message.INPUT_PROMOTION_FAILED;

import store.order.service.ConfirmListener;
import store.presentation.view.input.abstractview.BooleanInputView;

public class PromotionFailedInputView extends BooleanInputView implements
    ConfirmListener<String, Integer> {

    @Override
    protected void printLabel(Object... args) {
        System.out.println(INPUT_PROMOTION_FAILED.get(args));
    }

    @Override
    public Boolean apply(String itemName, Integer amount) {
        return super.read(itemName, amount);
    }
}
