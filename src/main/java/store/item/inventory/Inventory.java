package store.item.inventory;

import java.util.List;
import store.item.domain.Item;

public interface Inventory {

    void addItem(Item item);

    List<Item> getAllItems();
}
