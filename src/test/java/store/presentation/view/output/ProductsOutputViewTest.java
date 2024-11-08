package store.presentation.view.output;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import store.discount.promotion.domain.Promotion;
import store.item.domain.Item;
import store.item.domain.NormalItem;
import store.item.domain.PromotionItem;
import store.item.inventory.Inventory;
import store.presentation.view.IOTest;

class ProductsOutputViewTest extends IOTest {

    private final ProductsOutputView view = new ProductsOutputView();
    private Inventory mockInventory = new Inventory() {
        @Override
        public void addItem(Item item) {
        }

        @Override
        public List<Item> getAllItems() {
            return List.of(
                new NormalItem("itemA", 1000, 10),
                new PromotionItem("itemA", 1000, 20,
                    new Promotion("Promotion2+1", 2, 1, now(), now())),
                new NormalItem("itemB", 1000, 0)
            );
        }
    };

    @Test
    void 보유중인_상품_헤더문구를_띄운다() throws Exception {
        view.printHeader();
        assertThat(getOutput()).isEqualTo(
            "현재 보유하고 있는 상품입니다." + LINE_SEPARATOR + LINE_SEPARATOR);
    }

    @Test
    void 상품명_가격_재고_프로모션을_안내한다() throws Exception {
        view.printBody(mockInventory);

        String expectedOutput =
            "- itemA 1,000원 20개 Promotion2+1" + LINE_SEPARATOR
            + "- itemA 1,000원 10개" + LINE_SEPARATOR
            + "- itemB 1,000원 재고 없음" + LINE_SEPARATOR;
        assertThat(getOutput()).isEqualTo(expectedOutput);
    }
}