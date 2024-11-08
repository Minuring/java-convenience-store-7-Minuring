package store.presentation.view.output;

import store.presentation.view.output.abstractview.ArgumentOutputView;

public class ExceptionOutputView extends ArgumentOutputView<IllegalArgumentException> {

    @Override
    protected void printBody(IllegalArgumentException target) {
        System.out.println(target.getMessage());
    }
}
