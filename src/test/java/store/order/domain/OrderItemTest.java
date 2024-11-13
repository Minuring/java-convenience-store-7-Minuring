package store.order.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderItemTest {

    private OrderItem orderItemA = new OrderItem("itemA", 1000, 3, 1);
    private OrderItem orderItemB = new OrderItem("itemB", 900, 4, 0);
    private OrderItem orderItemC = new OrderItem("itemC", 500, 7, 2);

    @DisplayName("증정 상품이 있는 지 여부를 알 수 있다.")
    @Test
    public void hasFree() {
        assertThat(orderItemA.hasFree()).isTrue();
        assertThat(orderItemB.hasFree()).isFalse();
        assertThat(orderItemC.hasFree()).isTrue();
    }

    @DisplayName("총 구매액을 계산할 수 있다.")
    @Test
    public void getTotalPrice() {
        assertThat(orderItemA.getTotalPrice()).isEqualTo(3000);
        assertThat(orderItemB.getTotalPrice()).isEqualTo(3600);
        assertThat(orderItemC.getTotalPrice()).isEqualTo(3500);
    }

    @DisplayName("총 프로모션 할인액을 계산할 수 있다.")
    @Test
    public void getPromotionDiscountPrice() {
        assertThat(orderItemA.getPromotionDiscountPrice()).isEqualTo(1000);
        assertThat(orderItemB.getPromotionDiscountPrice()).isEqualTo(0);
        assertThat(orderItemC.getPromotionDiscountPrice()).isEqualTo(1000);
    }

}