package store.presentation.processor;

import java.util.List;
import store.discount.membership.MembershipImpl;
import store.dto.BuyRequest;
import store.order.domain.Order;
import store.order.service.OrderService;
import store.presentation.view.input.MembershipInputView;
import store.presentation.view.input.ProductsInputView;

public class OrderFacade {

    private final InputProcessor inputProcessor;
    private final OrderService orderService;
    private final ProductsInputView productsInputView = new ProductsInputView();
    private final MembershipInputView membershipInputView = new MembershipInputView();

    public OrderFacade(InputProcessor inputProcessor, OrderService orderService) {
        this.inputProcessor = inputProcessor;
        this.orderService = orderService;
    }

    public Order process() {
        List<BuyRequest> buyRequests = inputProcessor.process(productsInputView);
        Order order = orderService.order(buyRequests);

        Boolean applyMembership = inputProcessor.process(membershipInputView);
        if (applyMembership) {
            order.discountMembership(new MembershipImpl());
        }

        return order;
    }
}
