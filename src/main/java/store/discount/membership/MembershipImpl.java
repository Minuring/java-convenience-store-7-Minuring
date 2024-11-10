package store.discount.membership;

import static store.constant.ConstantNumbers.MAX_MEMBERSHIP;
import static store.constant.ConstantNumbers.PERCENT_MEMBERSHIP;

public class MembershipImpl implements Membership {

    @Override
    public int discount(int amount) {
        double discountAmount = Math.min(
            amount * percentToRate(PERCENT_MEMBERSHIP), MAX_MEMBERSHIP
        );
        return (int) Math.round(discountAmount);
    }

    private double percentToRate(double percent) {
        return percent * 0.01;
    }
}
