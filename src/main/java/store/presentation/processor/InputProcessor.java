package store.presentation.processor;

import store.presentation.view.input.abstractview.InputView;
import store.presentation.view.output.ExceptionOutputView;

public class InputProcessor {

    private final ExceptionOutputView EXCEPTION_OUTPUT_VIEW = new ExceptionOutputView();

    public <T> T process(InputView<T> inputView) {
        while (true) {
            try {
                return inputView.read();
            } catch (IllegalArgumentException exception) {
                EXCEPTION_OUTPUT_VIEW.print(exception);
            }
        }
    }
}
