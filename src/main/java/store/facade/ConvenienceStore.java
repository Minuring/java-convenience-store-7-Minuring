package store.facade;

import java.io.IOException;
import store.config.StoreInitializer;
import store.discount.promotion.domain.Promotions;
import store.discount.promotion.domain.PromotionsImpl;
import store.item.inventory.Inventory;
import store.order.domain.Order;
import store.order.exception.OrderCanceledException;
import store.presentation.view.input.BuyMoreInputView;
import store.presentation.view.output.OrderOutputView;
import store.presentation.view.output.ProductsOutputView;
import store.presentation.view.output.WelcomeOutputView;

public class ConvenienceStore {

    private final OrderFacade orderFacade;
    private final Inventory inventory;

    private final WelcomeOutputView welcomeOutputView = new WelcomeOutputView();
    private final ProductsOutputView productsOutputView = new ProductsOutputView();
    private final OrderOutputView orderOutputView = new OrderOutputView();
    private final BuyMoreInputView buyMoreInputView = new BuyMoreInputView();

    public ConvenienceStore(OrderFacade orderFacade, Inventory inventory) {
        this.orderFacade = orderFacade;
        this.inventory = inventory;
    }

    public void setUp() {
        Promotions PROMOTIONS = new PromotionsImpl();
        try {
            StoreInitializer.initialize(PROMOTIONS, inventory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void enter() {
        do {
            welcomeOutputView.print();
            productsOutputView.print(inventory);
            orderHandlingCancelException();
        }
        while (ExceptionFacade.process(buyMoreInputView::read));
    }

    private void orderHandlingCancelException() {
        try {
            Order order = orderFacade.process();
            orderOutputView.print(order);
        } catch (OrderCanceledException exception) {
        }
    }
}
