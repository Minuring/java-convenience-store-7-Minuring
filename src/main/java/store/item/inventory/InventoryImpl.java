package store.item.inventory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import store.item.domain.Item;
import store.item.domain.NormalItem;
import store.item.domain.PromotionItem;
import store.item.exception.ItemNotFoundException;

public class InventoryImpl implements Inventory {

    private final List<Item> inventory = new ArrayList<>();

    @Override
    public void addItem(Item item) {
        inventory.add(item);
    }

    @Override
    public PromotionItem getPromotionByName(String itemName) {
        return inventory.stream()
            .filter(item -> item instanceof PromotionItem)
            .map(item -> (PromotionItem) item)
            .findAny()
            .orElseThrow(ItemNotFoundException::new);
    }

    @Override
    public NormalItem getNormalByName(String itemName) {
        return inventory.stream()
            .filter(item -> item instanceof NormalItem)
            .map(item -> (NormalItem) item)
            .findAny()
            .orElseThrow(ItemNotFoundException::new);
    }

    @Override
    public int getTotalStockByName(String itemName) {
        NormalItem normalItem = getNormalByName(itemName);
        PromotionItem promotionItem = getPromotionByName(itemName);
        return normalItem.getStock() + promotionItem.getStock();
    }

    @Override
    public List<Item> getAllItems() {
        return Collections.unmodifiableList(inventory);
    }
}
