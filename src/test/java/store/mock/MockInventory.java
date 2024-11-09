package store.mock;

import static java.time.LocalDate.now;

import java.util.List;
import java.util.Optional;
import store.discount.promotion.domain.Promotion;
import store.item.domain.Item;
import store.item.domain.NormalItem;
import store.item.domain.PromotionItem;
import store.item.inventory.Inventory;

public class MockInventory implements Inventory {

    @Override
    public void addItem(Item item) {

    }

    @Override
    public boolean hasItemByName(String itemName) {
        return false;
    }

    @Override
    public Optional<PromotionItem> findPromotionItemByName(String itemName) {
        return Optional.empty();
    }

    @Override
    public Optional<NormalItem> findNormalItemByName(String itemName) {
        return Optional.empty();
    }

    @Override
    public int getTotalStockByName(String itemName) {
        return 0;
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
}
