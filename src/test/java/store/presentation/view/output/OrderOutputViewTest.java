package store.presentation.view.output;

import static org.assertj.core.api.Assertions.assertThat;
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

import org.junit.jupiter.api.Test;
import store.order.domain.Order;
import store.order.domain.OrderItem;
import store.presentation.view.IOTest;

class OrderOutputViewTest extends IOTest {

    private final OrderOutputView view = new OrderOutputView();
    private Order order = new Order();

    public OrderOutputViewTest() {
        order.addOrderItem(new OrderItem("itemA", 1000, 10, 3));
        order.addOrderItem(new OrderItem("itemB", 2000, 3, 1));
    }

    @Test
    void 편의점_헤더문구를_띄운다() throws Exception {
        view.printHeader();
        assertThat(getOutput()).isEqualToIgnoringNewLines(RECEIPT_HEADER.get());
    }

    @Test
    void 상품을_출력한다() {
        view.print(order);

        assertThat(getOutput()).contains(
            RECEIPT_PRODUCTS_HEADER.get(),
            RECEIPT_PRODUCT.get("itemA", 10, 1000),
            RECEIPT_PRODUCT.get("itemB", 3, 2000));
    }

    @Test
    void 증정상품을_출력한다() {
        view.print(order);

        assertThat(getOutput()).contains(
            RECEIPT_PROMOTION_HEADER.get(),
            RECEIPT_PROMOTION.get("itemA", 3),
            RECEIPT_PROMOTION.get("itemB", 1)
        );
    }

    @Test
    void 금액정보를_출력한다() {
        view.print(order);

        assertThat(getOutput()).contains(
            RECEIPT_SEPARATOR.get(),
            RECEIPT_TOTAL_PRICE.get(13, 16000),
            RECEIPT_PROMOTION_DISCOUNT.get(5000),
            RECEIPT_MEMBERSHIP_DISCOUNT.get(0),
            RECEIPT_PAY.get(11000)
        );
    }
}