package store.presentation.view.output;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import store.item.inventory.Inventory;
import store.mock.MockInventory;
import store.presentation.view.IOTest;

class ProductsOutputViewTest extends IOTest {

    private final ProductsOutputView view = new ProductsOutputView();
    private Inventory inventory = new MockInventory();

    @Test
    void 보유중인_상품_헤더문구를_띄운다() throws Exception {
        view.printHeader();
        assertThat(getOutput()).isEqualTo(
            "현재 보유하고 있는 상품입니다." + LINE_SEPARATOR + LINE_SEPARATOR);
    }

    @Test
    void 상품명_가격_재고_프로모션을_안내한다() throws Exception {
        view.printBody(inventory);

        String expectedOutput =
            "- itemA 1,000원 20개 Promotion2+1" + LINE_SEPARATOR
                + "- itemA 1,000원 10개" + LINE_SEPARATOR
                + "- itemB 1,000원 재고 없음" + LINE_SEPARATOR;
        assertThat(getOutput()).isEqualTo(expectedOutput);
    }
}