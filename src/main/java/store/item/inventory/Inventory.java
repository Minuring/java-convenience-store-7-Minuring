package store.item.inventory;

import java.util.List;
import java.util.Optional;
import store.item.domain.Item;
import store.item.domain.NormalItem;
import store.item.domain.PromotionItem;

public interface Inventory {

    void addItem(Item item);

    boolean hasItemByName(String itemName);

    Optional<PromotionItem> findPromotionItemByName(String itemName);

    Optional<NormalItem> findNormalItemByName(String itemName);

    int getTotalStockByName(String itemName);

    List<Item> getAllItems();
}
