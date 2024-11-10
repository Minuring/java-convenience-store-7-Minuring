package store.config;

import java.io.IOException;
import store.ConvenienceStore;
import store.discount.promotion.domain.Promotions;
import store.discount.promotion.domain.PromotionsImpl;
import store.item.inventory.Inventory;
import store.item.inventory.InventoryImpl;
import store.order.service.ConfirmListener;
import store.order.service.OrderService;
import store.order.service.OrderServiceImpl;
import store.presentation.processor.InputProcessor;
import store.presentation.processor.OrderFacade;
import store.presentation.view.input.PromotionAppendInputView;
import store.presentation.view.input.PromotionFailedInputView;

public class AppConfig {

    public static final Inventory INVENTORY = new InventoryImpl();
    public static final Promotions PROMOTIONS = new PromotionsImpl();
    public static final InputProcessor INPUT_PROCESSOR = new InputProcessor();

    private static final ConfirmListener<String, Integer> promotionAppendListener = new PromotionAppendInputView();
    private static final ConfirmListener<String, Integer> promotionFailedListener = new PromotionFailedInputView();
    public static final OrderService ORDER_SERVICE = new OrderServiceImpl(INVENTORY,
        promotionAppendListener, promotionFailedListener);

    public static final OrderFacade ORDER_FACADE = new OrderFacade(INPUT_PROCESSOR, ORDER_SERVICE);
    public static final ConvenienceStore CONVENIENCE_STORE = new ConvenienceStore(INPUT_PROCESSOR,
        ORDER_FACADE, INVENTORY);

    static {
        try {
            StoreInitializer.initialize(PROMOTIONS, INVENTORY);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private AppConfig() {
    }
}
