package store.item.inventory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import store.item.domain.Item;

public class InventoryImpl implements Inventory {

    private final List<Item> inventory = new ArrayList<>();

    @Override
    public void addItem(Item item) {
        inventory.add(item);
    }

    @Override
    public List<Item> getAllItems() {
        return Collections.unmodifiableList(inventory);
    }
}
