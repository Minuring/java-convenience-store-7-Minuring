package store.presentation.view.output;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import store.item.exception.ItemNotFoundException;
import store.presentation.view.IOTest;

class ExceptionOutputViewTest extends IOTest {

    private final ExceptionOutputView view = new ExceptionOutputView();

    @Test
    void ItemNotFoundException() throws Exception {
        view.printBody(new ItemNotFoundException());
        assertThat(getOutput()).isEqualTo("[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요." + LINE_SEPARATOR);
    }
}