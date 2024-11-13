package store.presentation.view.output;

import store.presentation.view.output.abstractview.ArgumentOutputView;

public class ExceptionOutputView extends ArgumentOutputView<RuntimeException> {

    @Override
    protected void printBody(RuntimeException target) {
        System.out.println(target.getMessage());
    }
}
