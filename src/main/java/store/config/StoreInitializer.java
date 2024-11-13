package store.config;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import store.common.collector.ItemCollector;
import store.common.collector.PromotionCollector;
import store.constant.FilePath;
import store.discount.promotion.domain.Promotion;
import store.discount.promotion.domain.Promotions;
import store.item.domain.Item;
import store.item.domain.NormalItem;
import store.item.domain.PromotionItem;
import store.item.inventory.Inventory;

public class StoreInitializer {

    public static void initialize(Promotions promotions, Inventory inventory) throws IOException {
        initializePromotions(promotions);
        initializeInventory(promotions, inventory);
    }

    private static void initializePromotions(Promotions promotions) throws IOException {
        PromotionCollector promotionCollector = new PromotionCollector(FilePath.PROMOTIONS);
        List<Promotion> collectedPromotions = promotionCollector.collect();
        collectedPromotions.forEach(promotions::addPromotion);
    }

    private static void initializeInventory(Promotions promotions, Inventory inventory)
        throws IOException {
        ItemCollector itemCollector = new ItemCollector(FilePath.PRODUCTS, promotions);
        List<Item> collectedItems = itemCollector.collect();
        collectedItems.forEach(inventory::addItem);

        List<Item> promotionItems = collectedItems.stream()
            .filter(item -> item instanceof PromotionItem).toList();

        addAllNoStockNormalItems(inventory, promotionItems);
    }

    private static void addAllNoStockNormalItems(Inventory inventory, List<Item> promotionItems) {
        promotionItems.forEach(item -> {
            Optional<NormalItem> normalItem = inventory.findNormalItemByName(item.getName());
            if (normalItem.isEmpty()) {
                inventory.addItem(new NormalItem(item.getName(), item.getPrice(), 0));
            }
        });
    }
}
