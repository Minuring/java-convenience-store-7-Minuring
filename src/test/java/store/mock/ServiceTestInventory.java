package store.mock;

import java.time.LocalDate;
import store.discount.promotion.domain.Promotion;
import store.item.domain.NormalItem;
import store.item.domain.PromotionItem;
import store.item.inventory.InventoryImpl;

public class ServiceTestInventory extends InventoryImpl {

    public ServiceTestInventory() {
        addItemA();
        addItemB();
        addItemC();
        addItemD();
    }

    private void addItemA() {
        Promotion promotionA = new Promotion("프로모션A1+1", 1, 1,
            LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31));
        this.addItem(new PromotionItem("itemA", 1000, 10, promotionA));
        this.addItem(new NormalItem("itemA", 1000, 10));
    }

    private void addItemB() {
        Promotion promotionB = new Promotion("프로모션B2+1", 2, 1,
            LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31));
        this.addItem(new PromotionItem("itemB", 1500, 10, promotionB));
        this.addItem(new NormalItem("itemB", 1500, 10));
    }

    private void addItemC() {
        Promotion promotionC = new Promotion("프로모션C1+1", 1, 1,
            LocalDate.of(2024, 1, 1), LocalDate.of(2024, 10, 31));
        this.addItem(new PromotionItem("itemC", 2000, 10, promotionC));
        this.addItem(new NormalItem("itemC", 2000, 10));
    }

    private void addItemD() {
        Promotion promotionD = new Promotion("프로모션D2+1", 2, 1,
            LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31));
        this.addItem(new PromotionItem("itemD", 1000, 0, promotionD));
        this.addItem(new NormalItem("itemD", 1000, 10));
    }
}
