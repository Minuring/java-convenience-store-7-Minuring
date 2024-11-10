package store.order.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Order {

    private final List<OrderItem> orderItems = new ArrayList<>();
    private int membershipDiscount = 0;

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
    }

    public int getTotalQuantity() {
        return orderItems.stream().mapToInt(OrderItem::getQuantity).sum();
    }

    public int getTotalPrice() {
        return orderItems.stream().mapToInt(OrderItem::getTotalPrice).sum();
    }

    public int getTotalDiscount() {
        return orderItems.stream().mapToInt(OrderItem::getPromotionDiscountPrice).sum();
    }

    public int getMembershipDiscount() {
        return membershipDiscount;
    }

    public int getActualPrice() {
        return getTotalPrice() - getTotalDiscount() - membershipDiscount;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public Map<String, Integer> getFreeItems() {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        orderItems.stream()
            .filter(OrderItem::hasFree)
            .forEach(orderItem -> {
                String name = orderItem.getName();
                int free = orderItem.getFree();
                map.put(name, free);
            }
        );

        return map;
    }
}
