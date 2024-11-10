package store;

import store.item.inventory.Inventory;
import store.order.domain.Order;
import store.presentation.processor.InputProcessor;
import store.presentation.processor.OrderFacade;
import store.presentation.view.input.BuyMoreInputView;
import store.presentation.view.output.OrderOutputView;
import store.presentation.view.output.ProductsOutputView;
import store.presentation.view.output.WelcomeOutputView;

public class ConvenienceStore {

    private final InputProcessor inputProcessor;
    private final OrderFacade orderFacade;
    private final Inventory inventory;

    private final WelcomeOutputView welcomeOutputView = new WelcomeOutputView();
    private final ProductsOutputView productsOutputView = new ProductsOutputView();
    private final OrderOutputView orderOutputView = new OrderOutputView();
    private final BuyMoreInputView buyMoreInputView = new BuyMoreInputView();

    public ConvenienceStore(InputProcessor inputProcessor, OrderFacade orderFacade,
        Inventory inventory) {
        this.inputProcessor = inputProcessor;
        this.orderFacade = orderFacade;
        this.inventory = inventory;
    }

    public void enter() {
        do {
            welcomeOutputView.print();
            productsOutputView.print(inventory);
            Order order = orderFacade.process();
            orderOutputView.print(order);
        }
        while (inputProcessor.process(buyMoreInputView));
    }
}
