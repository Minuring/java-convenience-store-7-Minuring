package store.presentation.view.input;

import camp.nextstep.edu.missionutils.Console;

public abstract class InputView<T> {

    public T read(Object... args) throws IllegalArgumentException {
        printLabel(args);
        String input = readInput();
        return convert(input);
    }

    private static String readInput() {
        return Console.readLine();
    }

    protected abstract void printLabel(Object... args);

    protected abstract T convert(String input);
}
