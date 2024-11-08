package store.item.inventory;

import java.util.List;
import store.item.domain.Item;
import store.item.domain.NormalItem;
import store.item.domain.PromotionItem;

public interface Inventory {

    void addItem(Item item);

    PromotionItem getPromotionByName(String itemName);

    NormalItem getNormalByName(String itemName);

    List<Item> getAllItems();
}
