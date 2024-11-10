package store.presentation.view.output;

import static store.presentation.view.Message.RECEIPT_HEADER;
import static store.presentation.view.Message.RECEIPT_MEMBERSHIP_DISCOUNT;
import static store.presentation.view.Message.RECEIPT_PAY;
import static store.presentation.view.Message.RECEIPT_PRODUCT;
import static store.presentation.view.Message.RECEIPT_PRODUCTS_HEADER;
import static store.presentation.view.Message.RECEIPT_PROMOTION;
import static store.presentation.view.Message.RECEIPT_PROMOTION_DISCOUNT;
import static store.presentation.view.Message.RECEIPT_PROMOTION_HEADER;
import static store.presentation.view.Message.RECEIPT_SEPARATOR;
import static store.presentation.view.Message.RECEIPT_TOTAL_PRICE;

import store.order.domain.Order;
import store.presentation.view.output.abstractview.ArgumentOutputView;

public class OrderOutputView extends ArgumentOutputView<Order> {

    @Override
    protected void printHeader() {
        System.out.println(RECEIPT_HEADER.get());
    }

    @Override
    protected void printBody(Order order) {
        printProducts(order);
        printFrees(order);
        printStatistics(order);
    }

    private void printProducts(Order order) {
        System.out.println(RECEIPT_PRODUCTS_HEADER.get());
        order.getOrderItems().forEach(item -> {
            String message = RECEIPT_PRODUCT.get(item.getName(), item.getQuantity(),
                item.getPrice());
            System.out.println(message);
        });
    }

    private void printFrees(Order order) {
        System.out.println(RECEIPT_PROMOTION_HEADER.get());
        order.getFreeItems().forEach((itemName, count) -> {
            String message = RECEIPT_PROMOTION.get(itemName, count);
            System.out.println(message);
        });
    }

    private void printStatistics(Order order) {
        System.out.println(RECEIPT_SEPARATOR.get());
        System.out.println(
            RECEIPT_TOTAL_PRICE.get(order.getTotalQuantity(), order.getTotalPrice()));
        System.out.println(RECEIPT_PROMOTION_DISCOUNT.get(order.getTotalDiscount()));
        System.out.println(RECEIPT_MEMBERSHIP_DISCOUNT.get(order.getMembershipDiscount()));
        System.out.println(RECEIPT_PAY.get(order.getActualPrice()));
    }
}
