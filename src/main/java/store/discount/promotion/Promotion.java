package store.discount.promotion;

import static store.constant.Constant.EXACT_GET_PROMOTION;
import static store.constant.Constant.MAX_BUY_PROMOTION;
import static store.constant.Constant.MAX_LENGTH_PROMOTION_NAME;
import static store.constant.Constant.MIN_BUY_PROMOTION;
import static store.constant.Constant.MIN_LENGTH_PROMOTION_NAME;
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

    public Promotion(String name, int buy, int get, LocalDate startDate,
        LocalDate endDate) {
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

    private static void validateName(String name) {
        if (name == null
            || name.length() < MIN_LENGTH_PROMOTION_NAME
            || name.length() > MAX_LENGTH_PROMOTION_NAME) {
            throw new IllegalArgumentException(LENGTH_PROMOTION_NAME.message());
        }
    }

    private static void validateBuy(int buy) {
        if (buy < MIN_BUY_PROMOTION || buy > MAX_BUY_PROMOTION) {
            throw new IllegalArgumentException(Errors.LENGTH_PROMOTION_BUY.message());
        }
    }

    private static void validateGet(int get) {
        if (get != EXACT_GET_PROMOTION) {
            throw new IllegalArgumentException(EXACT_PROMOTION_GET.message());
        }
    }
}
