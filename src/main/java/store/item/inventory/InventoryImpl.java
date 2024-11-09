package store.item.inventory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import store.item.domain.Item;
import store.item.domain.NormalItem;
import store.item.domain.PromotionItem;

public class InventoryImpl implements Inventory {

    private final List<Item> inventory = new ArrayList<>();

    @Override
    public void addItem(Item item) {
        inventory.add(item);
    }

    @Override
    public boolean hasItemByName(String itemName) {
        return inventory.stream()
            .map(Item::getName)
            .anyMatch(itemName::equals);
    }

    @Override
    public Optional<PromotionItem> findPromotionItemByName(String itemName) {
        return getByName(itemName)
            .filter(item -> item instanceof PromotionItem)
            .map(item -> (PromotionItem) item)
            .findAny();
    }

    @Override
    public Optional<NormalItem> findNormalItemByName(String itemName) {
        return getByName(itemName)
            .filter(item -> item instanceof NormalItem)
            .map(item -> (NormalItem) item)
            .findAny();
    }

    private Stream<Item> getByName(String itemName) {
        return inventory.stream()
            .filter(item -> item.getName().equals(itemName));
    }

    @Override
    public int getTotalStockByName(String itemName) {
        int totalStock = 0;
        totalStock += findNormalItemByName(itemName).map(Item::getStock).orElse(0);
        totalStock += findPromotionItemByName(itemName).map(Item::getStock).orElse(0);

        return totalStock;
    }

    @Override
    public List<Item> getAllItems() {
        return Collections.unmodifiableList(inventory);
    }
}
