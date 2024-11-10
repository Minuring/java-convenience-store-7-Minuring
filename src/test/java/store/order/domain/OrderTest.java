package store.order.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import store.discount.membership.MembershipImpl;

class OrderTest {

    private final Order order = new Order();

    private OrderTest() {
        order.addOrderItem(new OrderItem("itemA", 1000, 5, 2));
        order.addOrderItem(new OrderItem("itemB", 700, 3, 1));
        order.addOrderItem(new OrderItem("itemC", 500, 5, 0));
        order.applyMembership(new MembershipImpl());
    }

    @Test
    void 총구매액을_계산한다() {
        assertThat(order.getTotalPrice()).isEqualTo(5000 + 2100 + 2500);
    }

    @Test
    void 총할인액을_계산한다() {
        assertThat(order.getTotalDiscount()).isEqualTo(2000 + 700);
    }
}