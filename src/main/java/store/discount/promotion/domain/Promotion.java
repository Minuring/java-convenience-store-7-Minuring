package store.discount.promotion.domain;

import static store.constant.ConstantNumbers.EXACT_GET_PROMOTION;
import static store.constant.ConstantNumbers.MAX_BUY_PROMOTION;
import static store.constant.ConstantNumbers.MAX_LENGTH_PROMOTION_NAME;
import static store.constant.ConstantNumbers.MIN_BUY_PROMOTION;
import static store.constant.ConstantNumbers.MIN_LENGTH_PROMOTION_NAME;
import static store.constant.Errors.EXACT_PROMOTION_GET;
import static store.constant.Errors.LENGTH_PROMOTION_NAME;

import java.time.LocalDate;
import java.time.LocalDateTime;
import store.constant.Errors;

public class Promotion {

    private final String name;
    private final int buy;
    private final int get;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Promotion(String name, int buy, int get, LocalDate startDate, LocalDate endDate) {
        validateName(name);
        validateBuy(buy);
        validateGet(get);

        this.name = name;
        this.buy = buy;
        this.get = get;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isInTime(LocalDateTime time) {
        LocalDate timeLocalDate = time.toLocalDate();
        return startDate.isEqual(timeLocalDate)
            || endDate.isEqual(timeLocalDate)
            || (startDate.isBefore(timeLocalDate) && endDate.isAfter(timeLocalDate));
    }

    public int getUnit() {
        return get + buy;
    }

    private static void validateName(String name) {
        if (name == null
            || name.length() < MIN_LENGTH_PROMOTION_NAME.get()
            || name.length() > MAX_LENGTH_PROMOTION_NAME.get()) {
            throw new IllegalArgumentException(LENGTH_PROMOTION_NAME.message());
        }
    }

    private static void validateBuy(int buy) {
        if (buy < MIN_BUY_PROMOTION.get() || buy > MAX_BUY_PROMOTION.get()) {
            throw new IllegalArgumentException(Errors.LENGTH_PROMOTION_BUY.message());
        }
    }

    private static void validateGet(int get) {
        if (get != EXACT_GET_PROMOTION.get()) {
            throw new IllegalArgumentException(EXACT_PROMOTION_GET.message());
        }
    }

    public String getName() {
        return name;
    }
}
