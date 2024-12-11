package store.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.function.Supplier;
import store.StoreException;

public abstract class InputViewTemplate<T> {

    public T readOnce(Object... args) {
        printHeader(args);
        String input = Console.readLine();
        return bind(input);
    }

    public T readUntilSuccess(Object... args) {
        return readUntilSuccess(() -> readOnce(args));
    }

    protected T readUntilSuccess(Supplier<T> inputSupplier) {
        while (true) {
            try {
                return inputSupplier.get();
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            } catch (StoreException e) {
                OutputView.printErrorMessage(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    public abstract void printHeader(Object... args);

    public abstract T bind(String userInput);
}