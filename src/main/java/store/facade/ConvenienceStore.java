package store.facade;

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
