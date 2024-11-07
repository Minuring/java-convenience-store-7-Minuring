package store.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import store.constant.Errors;
import store.discount.promotion.domain.Promotions;
import store.discount.promotion.domain.PromotionsImpl;
import store.item.inventory.Inventory;
import store.item.inventory.InventoryImpl;

class StoreInitializerTest {

    @Test
    void initialize() throws IOException {
        Promotions promotions = new PromotionsImpl();
        Inventory inventory = new InventoryImpl();
        StoreInitializer.initialize(promotions, inventory);

        assertThat(inventory.getAllItems()).hasSize(16);
        assertDoesNotThrow(() -> promotions.getByName("탄산2+1"),
            Errors.NOT_FOUND_PROMOTION::message);
    }
}