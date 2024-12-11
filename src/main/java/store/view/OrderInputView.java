package store.view;

import java.util.regex.Pattern;
import store.order.OrderCart;
import store.order.OrderCart.OrderProduct;
import store.view.exception.OrderFormatException;

public class OrderInputView extends InputViewTemplate<OrderCart> {

    private static final Pattern PATTERN = Pattern.compile(
        "^\\[[a-zA-Z가-힣]+-\\d+\\](,\\[[a-zA-Z가-힣]+-\\d+\\])*$");

    @Override
    public void printHeader(Object... args) {
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
    }

    @Override
    public OrderCart bind(String userInput) {
        validateFormat(userInput);
        OrderCart cart = new OrderCart();

        String[] orders = userInput.split(",");
        for (String order : orders) {
            cart.addProduct(createOrderProduct(order));
        }

        return cart;
    }

    private static OrderProduct createOrderProduct(String order) {
        order = order.substring(1, order.length() - 1);
        String[] split = order.split("-");
        String name = split[0];
        int quantity = Integer.parseInt(split[1]);
        return new OrderProduct(name, quantity);
    }

    private static void validateFormat(String userInput) {
        if (!PATTERN.matcher(userInput).matches()) {
            throw new OrderFormatException();
        }
    }
}
