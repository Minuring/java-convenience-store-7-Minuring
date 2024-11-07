package store.presentation.view.input;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import store.dto.BuyRequest;

class ProductsInputViewTest {
    @Test
    void 상품_이름과_수량을_입력한다() throws Exception {
        InputTestManager.setReadLine("[사이다-2],[감자칩-1]");
        ProductsInputView productsInputView = new ProductsInputView();

        List<BuyRequest> buyRequests = productsInputView.read();
        BuyRequest first = buyRequests.getFirst();
        BuyRequest last = buyRequests.getLast();

        assertThat(first.getItemName()).isEqualTo("사이다");
        assertThat(first.getAmount()).isEqualTo(2);
        assertThat(last.getItemName()).isEqualTo("감자칩");
        assertThat(last.getAmount()).isEqualTo(1);
    }

    @Test
    void t() throws Exception {
        ProductsInputView productsInputView = new ProductsInputView();
        List<BuyRequest> read = productsInputView.read();
    }
}