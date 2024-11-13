package store.presentation.view.output;

import static org.assertj.core.api.Assertions.assertThat;
import static store.presentation.view.Message.PRODUCT;
import static store.presentation.view.Message.PRODUCTS_HEADER;
import static store.presentation.view.Message.PRODUCT_NO_STOCK;

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
        assertThat(getOutput()).contains(PRODUCTS_HEADER.get());
    }

    @Test
    void 상품명_가격_재고_프로모션을_안내한다() throws Exception {
        view.printBody(inventory);

        assertThat(getOutput()).containsSubsequence(
            PRODUCT.get("itemA", 1000, 20) + " Promotion2+1",
            PRODUCT.get("itemA", 1000, 10),
            PRODUCT_NO_STOCK.get("itemB", 1000)
        );
    }
}