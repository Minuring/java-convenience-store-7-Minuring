package store.mock;

import static java.time.LocalDate.now;

import java.util.List;
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
    public PromotionItem getPromotionByName(String itemName) {
        return null;
    }

    @Override
    public NormalItem getNormalByName(String itemName) {
        return null;
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
