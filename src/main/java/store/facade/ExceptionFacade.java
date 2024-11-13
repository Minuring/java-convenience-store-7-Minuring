package store.facade;

import java.util.function.Supplier;
import store.presentation.view.output.ExceptionOutputView;

public class ExceptionFacade {

    private static final ExceptionOutputView EXCEPTION_OUTPUT_VIEW = new ExceptionOutputView();

    public static <R> R process(Supplier<R> process) {
        while (true) {
            try {
                return process.get();
            } catch (IllegalArgumentException exception) {
                EXCEPTION_OUTPUT_VIEW.print(exception);
            }
        }
    }
}
