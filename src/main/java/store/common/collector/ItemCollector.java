package store.common.collector;

import store.constant.FilePath;
import store.discount.promotion.domain.Promotion;
import store.discount.promotion.domain.Promotions;
import store.item.domain.Item;
import store.item.domain.NormalItem;
import store.item.domain.PromotionItem;

public class ItemCollector extends FileContentCollector<Item> {

    private final Promotions promotions;
    public static final String PROMOTION_NOTHING = "null";

    public ItemCollector(FilePath filePath, Promotions promotions) {
        super(filePath);
        this.promotions = promotions;
    }

    @Override
    protected Item toInstance(String line, long sequence) {
        String[] split = line.trim().split(",");
        String name = split[0];
        int price = Integer.parseInt(split[1]);
        int quantity = Integer.parseInt(split[2]);
        String promotionName = split[3].trim();

        return generateItem(name, price, quantity, promotionName);
    }

    private Item generateItem(String name, int price, int quantity, String promotionName) {
        if (promotionName.equals(PROMOTION_NOTHING)) {
            return new NormalItem(name, price, quantity);
        }

        Promotion promotion = promotions.getByName(promotionName);
        return new PromotionItem(name, price, quantity, promotion);
    }
}
