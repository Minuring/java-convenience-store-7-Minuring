package store.presentation.view.input.abstractview;

import static store.presentation.view.ViewErrors.INVALID_FORMAT;

public abstract class BooleanInputView extends InputView<Boolean> {

    private static final String PATTERN_REGEX = "[YyNn]";

    @Override
    protected abstract void printLabel(Object... args);

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
