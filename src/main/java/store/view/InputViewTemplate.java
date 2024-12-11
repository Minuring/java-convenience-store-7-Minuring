package store.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.function.Supplier;
import store.StoreException;

public abstract class InputViewTemplate<T> {

    public T readOnce() {
        printHeader();
        String input = Console.readLine();
        return bind(input);
    }

    public T readUntilSuccess() {
        return readUntilSuccess(this::readOnce);
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

    public abstract void printHeader();

    public abstract T bind(String userInput);
}