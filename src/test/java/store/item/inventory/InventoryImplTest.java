package store.item.inventory;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.discount.promotion.domain.Promotion;
import store.item.domain.NormalItem;
import store.item.domain.PromotionItem;

class InventoryImplTest {

    private final Inventory inventory = new InventoryImpl();

    @DisplayName("상품을 추가할 수 있다.")
    @Test
    void addItem() {
        NormalItem item = new NormalItem("ABCD", 1000, 10);

        inventory.addItem(item);

        assertThat(inventory.getAllItems()).containsOnly(item);
    }

    @DisplayName("상품 이름으로 프로모션 상품을 검색할 수 있다.")
    @Test
    void getPromotionByName() {
        Promotion promotion = new Promotion("프로모션A", 2, 1, LocalDate.now(), LocalDate.now());
        PromotionItem item = new PromotionItem("ABCD", 1000, 10, promotion);

        inventory.addItem(item);

        assertThat(inventory.findPromotionItemByName("ABCD")).hasValue(item);
    }

    @DisplayName("상품 이름으로 일반 상품을 검색할 수 있다.")
    @Test
    void getNormalByName() {
        NormalItem item = new NormalItem("ABCD", 1000, 10);

        inventory.addItem(item);

        assertThat(inventory.findNormalItemByName("ABCD")).hasValue(item);
    }

    @DisplayName("같은 이름의 일반 상품과 프로모션 상품이 있을 때 재고의 합을 검색할 수 있다.")
    @Test
    void getTotalStockByName() {
        Promotion promotion = new Promotion("프로모션A", 2, 1, LocalDate.now(), LocalDate.now());
        NormalItem normalItem = new NormalItem("ABCD", 1000, 10);
        PromotionItem promotionItem = new PromotionItem("ABCD", 1000, 10, promotion);

        inventory.addItem(normalItem);
        inventory.addItem(promotionItem);

        assertThat(inventory.getTotalStockByName("ABCD")).isEqualTo(20);
    }

    @Test
    void 상품_이름으로_존재여부를_알_수_있다() throws Exception {
        NormalItem item = new NormalItem("ABCD", 1000, 10);
        inventory.addItem(item);

        boolean result = inventory.hasItemByName("ABCD");
        assertThat(result).isTrue();
    }
}