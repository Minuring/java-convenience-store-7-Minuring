package store.presentation.view.output.abstractview;

public abstract class ArgumentOutputView<T> {

    public void print(T target) {
        printHeader();
        printBody(target);
        printFooter();
    }

    protected void printHeader() {
    }

    protected abstract void printBody(T target);

    protected void printFooter() {
    }
}
