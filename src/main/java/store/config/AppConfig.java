package store.config;

import store.discount.membership.Membership;
import store.discount.membership.MembershipImpl;
import store.facade.ConvenienceStore;
import store.facade.OrderFacade;
import store.item.inventory.Inventory;
import store.item.inventory.InventoryImpl;
import store.order.service.ConfirmListener;
import store.order.service.OrderService;
import store.order.service.OrderServiceImpl;
import store.presentation.view.input.PromotionAppendInputView;
import store.presentation.view.input.PromotionFailedInputView;

public class AppConfig {

    public static final Inventory INVENTORY = new InventoryImpl();

    private static final ConfirmListener<String, Integer> promotionAppendListener = new PromotionAppendInputView();
    private static final ConfirmListener<String, Integer> promotionFailedListener = new PromotionFailedInputView();
    public static final OrderService ORDER_SERVICE = new OrderServiceImpl(INVENTORY,
        promotionAppendListener, promotionFailedListener);

    public static final OrderFacade ORDER_FACADE = new OrderFacade(ORDER_SERVICE);
    public static final ConvenienceStore CONVENIENCE_STORE = new ConvenienceStore(ORDER_FACADE,
        INVENTORY);

    public static final Membership MEMBERSHIP = new MembershipImpl();

    private AppConfig() {
    }
}
