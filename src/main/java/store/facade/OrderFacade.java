package store.facade;

import java.util.List;
import store.discount.membership.MembershipImpl;
import store.dto.BuyRequest;
import store.order.domain.Order;
import store.order.service.OrderService;
import store.presentation.view.input.MembershipInputView;
import store.presentation.view.input.ProductsInputView;

public class OrderFacade {

    private final OrderService orderService;
    private final ProductsInputView productsInputView = new ProductsInputView();
    private final MembershipInputView membershipInputView = new MembershipInputView();

    public OrderFacade(OrderService orderService) {
        this.orderService = orderService;
    }

    public Order process() {
        Order order = ExceptionFacade.process(() -> {
            List<BuyRequest> buyRequests = productsInputView.read();
            return orderService.order(buyRequests);
        });
        if (ExceptionFacade.process(membershipInputView::read)) {
            order.discountMembership(new MembershipImpl());
        }
        return order;
    }
}
