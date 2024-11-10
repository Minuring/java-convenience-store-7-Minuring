package store.presentation.view.output;

import static org.assertj.core.api.Assertions.assertThat;
import static store.constant.Errors.NOT_FOUND_ITEM;

import org.junit.jupiter.api.Test;
import store.item.exception.ItemNotFoundException;
import store.presentation.view.IOTest;

class ExceptionOutputViewTest extends IOTest {

    private final ExceptionOutputView view = new ExceptionOutputView();

    @Test
    void ItemNotFoundException() throws Exception {
        view.printBody(new ItemNotFoundException());
        assertThat(getOutput()).contains(NOT_FOUND_ITEM.message());
    }
}