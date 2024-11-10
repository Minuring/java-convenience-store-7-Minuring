package store.order.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private final List<OrderItem> orderItems = new ArrayList<>();

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
    }

    public int getTotalPrice() {
        return orderItems.stream().mapToInt(OrderItem::getTotalPrice).sum();
    }

    public int getTotalDiscount() {
        return orderItems.stream().mapToInt(OrderItem::getPromotionDiscountPrice).sum();
    }

    public int getActualPrice() {
        return getTotalPrice() - getTotalDiscount();
    }
}
