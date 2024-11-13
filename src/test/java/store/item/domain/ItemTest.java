package store.item.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static store.constant.Errors.NOT_ENOUGH_STOCK;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.item.exception.NotEnoughStockException;

class ItemTest {

    @DisplayName("재고가 충분하면 차감할 수 있다.")
    @Test
    void removeStock() {
        Item item = new NormalItem("itemA", 1000, 10);
        item.removeStock(10);

        assertThat(item.getStock()).isEqualTo(0);
    }

    @DisplayName("재고가 충분하지 않으면 예외가 발생한다.")
    @Test
    void removeStock_NotEnoughStock() throws Exception {
        Item item = new NormalItem("itemA", 1000, 10);

        assertThatThrownBy(() -> item.removeStock(11))
            .isInstanceOf(NotEnoughStockException.class)
            .hasMessage(NOT_ENOUGH_STOCK.message());
    }
}