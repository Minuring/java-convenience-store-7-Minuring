package store.presentation.view.output.abstractview;

public abstract class NoArgumentOutputView {

    public void print() {
        printHeader();
        printBody();
        printFooter();
    }

    protected void printHeader() {
    }

    protected abstract void printBody();

    protected void printFooter() {
    }
}
