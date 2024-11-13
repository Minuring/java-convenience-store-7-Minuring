package store.presentation.view.output;

import store.order.domain.Order;
import store.presentation.view.output.abstractview.ArgumentOutputView;

public class OrderOutputView extends ArgumentOutputView<Order> {

    protected static final String HEADER = "===========W 편의점=============";
    protected static final String PRODUCTS_HEADER =
        String.format("%-15s %-5s %-5s", "상품명", "수량", "금액");
    protected static final String PRODUCT = "%-15s %-5d %-,5d";
    protected static final String PROMOTION_HEADER = "===========증\t정=============";
    protected static final String PROMOTION = "%-15s %d";
    protected static final String SEPARATOR = "==============================";
    protected static final String TOTAL_PRICE = "%-15s %-5d %-,5d";
    protected static final String PROMOTION_DISCOUNT = "%-20s  -%-,5d";
    protected static final String MEMBERSHIP_DISCOUNT = "%-20s  -%-,5d";
    protected static final String PAY = "%-20s  %-,5d";

    @Override
    protected void printHeader() {
        System.out.println(HEADER);
    }

    @Override
    protected void printBody(Order order) {
        printProducts(order);
        if (order.getTotalDiscount() > 0) {
            printFrees(order);
        }
        printStatistics(order);
    }

    private void printProducts(Order order) {
        System.out.println(PRODUCTS_HEADER);
        order.getOrderItems().forEach(item -> {
            String name = item.getName();
            int quantity = item.getQuantity();
            int price = item.getPrice();
            System.out.printf(PRODUCT + "%n", name, quantity, price * quantity);
        });
    }

    private void printFrees(Order order) {
        System.out.println(PROMOTION_HEADER);
        order.getFreeItems().forEach((itemName, count) -> {
            String message = String.format(PROMOTION, itemName, count);
            System.out.println(message);
        });
    }

    private void printStatistics(Order order) {
        System.out.println(SEPARATOR);
        System.out.printf(TOTAL_PRICE + "%n", "총구매액",
            order.getTotalQuantity(), order.getTotalPrice());
        System.out.printf(PROMOTION_DISCOUNT + "%n", "행사할인", order.getTotalDiscount());
        System.out.printf(MEMBERSHIP_DISCOUNT + "%n", "멤버십할인", order.getMembershipDiscount());
        System.out.printf(PAY + "%n", "내실돈", order.getActualPrice());
    }
}
