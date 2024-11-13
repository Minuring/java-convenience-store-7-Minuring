package store.discount.membership;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class MembershipImplTest {

    @Test
    void 금액계산() {
        Membership membership = new MembershipImpl();
        int discount = membership.discount(10000);
        assertThat(discount).isEqualTo(3000);
    }

    @Test
    void 할인액이_8천원을_넘어갈수_없다() {
        Membership membership = new MembershipImpl();
        int discount = membership.discount(50000);
        assertThat(discount).isEqualTo(8000);
    }
}