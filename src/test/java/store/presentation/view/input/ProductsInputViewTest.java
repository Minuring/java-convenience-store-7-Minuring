package store.presentation.view.input;

import static org.assertj.core.api.Assertions.assertThat;
import static store.presentation.view.Message.INPUT_PRODUCTS;

import java.util.List;
import org.junit.jupiter.api.Test;
import store.dto.BuyRequest;
import store.presentation.view.IOTest;

class ProductsInputViewTest extends IOTest {

    private final ProductsInputView productsInputView = new ProductsInputView();

    @Test
    void 지정한_입력요구_문구를_띄운다() throws Exception {
        systemIn("[사이다-2]");

        productsInputView.read();

        assertThat(getOutput()).contains(INPUT_PRODUCTS.get());
    }

    @Test
    void 상품_이름과_수량을_입력한다() throws Exception {
        systemIn("[사이다-2],[감자칩-1]");
        List<BuyRequest> buyRequests = productsInputView.read();
        BuyRequest first = buyRequests.getFirst();
        BuyRequest last = buyRequests.getLast();

        assertThat(first.itemName()).isEqualTo("사이다");
        assertThat(first.amount()).isEqualTo(2);
        assertThat(last.itemName()).isEqualTo("감자칩");
        assertThat(last.amount()).isEqualTo(1);
    }
}