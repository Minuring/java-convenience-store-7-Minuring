package store.order.service;

import java.util.List;
import store.dto.BuyRequest;
import store.order.domain.Order;

public interface OrderService {

    Order order(List<BuyRequest> buyRequests);
}
