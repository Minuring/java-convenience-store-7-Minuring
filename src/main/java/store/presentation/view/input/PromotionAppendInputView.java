package store.presentation.view.input;

import static store.presentation.view.Message.INPUT_PROMOTION_APPEND;
import static store.presentation.view.ViewErrors.INVALID_FORMAT;

public class PromotionAppendInputView extends InputView<Boolean> {

    private static final String PATTERN_REGEX = "[YyNn]";

    @Override
    protected void printLabel(Object... args) {
        System.out.println(INPUT_PROMOTION_APPEND.get(args));
    }

    @Override
    protected Boolean convert(String input) {
        input = input.trim();
        validateFormat(input);
        return "Y".equalsIgnoreCase(input);
    }

    private void validateFormat(String input) {
        if (!input.matches(PATTERN_REGEX) || input.isEmpty()) {
            throw new IllegalArgumentException(INVALID_FORMAT.message());
        }
    }
}
