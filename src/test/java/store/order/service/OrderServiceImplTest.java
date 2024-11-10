package store.order.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import store.dto.BuyRequest;
import store.item.exception.ItemNotFoundException;
import store.item.exception.NotEnoughStockException;
import store.item.inventory.Inventory;
import store.mock.ServiceTestInventory;
import store.order.domain.Order;
import store.order.exception.OrderCanceledException;

class OrderServiceImplTest {

    private final ConfirmListener<String, Integer> yes = (s, i) -> true;
    private final ConfirmListener<String, Integer> no = (s, i) -> false;
    private Inventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new ServiceTestInventory();
    }

    @Test
    void 전체_재고보다_많은수량을_주문하면_예외가_발생한다() {
        OrderService orderService = new OrderServiceImpl(inventory, yes, yes);
        assertThatThrownBy(() -> orderService.order(List.of(new BuyRequest("itemA", 21))))
            .isInstanceOf(NotEnoughStockException.class);
    }

    @Test
    void 존재하지않는_이름의_상품을_구매요청하면_예외가_발생한다() {
        OrderService orderService = new OrderServiceImpl(inventory, yes, yes);
        assertThatThrownBy(() -> orderService.order(List.of(new BuyRequest("itemX", 1))))
            .isInstanceOf(ItemNotFoundException.class);
    }

    @ParameterizedTest
    @MethodSource("provideBuysAppendOK")
    void 프로모션_적용가능할때_수량만큼_가져오기로하면_추가한다(BuyRequest buyRequest,
        int expectTotalPrice, int expectTotalDiscount) {
        OrderService orderService = new OrderServiceImpl(inventory, yes, yes);

        Order order = orderService.order(List.of(buyRequest));

        assertThat(order.getTotalPrice()).isEqualTo(expectTotalPrice);
        assertThat(order.getTotalDiscount()).isEqualTo(expectTotalDiscount);
    }

    private static Stream<Arguments> provideBuysAppendOK() {
        return Stream.of(
            Arguments.of(new BuyRequest("itemA", 1), 2000, 1000),
            Arguments.of(new BuyRequest("itemA", 9), 10000, 5000),
            Arguments.of(new BuyRequest("itemB", 2), 4500, 1500),
            Arguments.of(new BuyRequest("itemB", 5), 9000, 3000),
            Arguments.of(new BuyRequest("itemB", 8), 13500, 4500));
    }

    @ParameterizedTest
    @MethodSource("provideBuysAppendNo")
    void 프로모션_적용가능할때_수량만큼_가져오지_않기로하고_정가결제하기로하면_정가결제한다(BuyRequest buyRequest,
        int expectTotalPrice, int expectTotalDiscount) {
        OrderService orderService = new OrderServiceImpl(inventory, no, yes);

        Order order = orderService.order(List.of(buyRequest));

        assertThat(order.getTotalPrice()).isEqualTo(expectTotalPrice);
        assertThat(order.getTotalDiscount()).isEqualTo(expectTotalDiscount);
    }

    @ParameterizedTest
    @MethodSource("provideBuysAppendNo")
    void 정가결제에서_취소하면_재고를_차감하지_않고_취소한다(BuyRequest buyRequest) {
        OrderService orderService = new OrderServiceImpl(inventory, no, no);

        int beforeStock = inventory.getTotalStockByName(buyRequest.itemName());
        assertThatThrownBy(() -> orderService.order(List.of(buyRequest)))
            .isInstanceOf(OrderCanceledException.class);
        int afterStock = inventory.getTotalStockByName(buyRequest.itemName());

        assertThat(afterStock).isEqualTo(beforeStock);
    }

    private static Stream<Arguments> provideBuysAppendNo() {
        return Stream.of(
            Arguments.of(new BuyRequest("itemA", 1), 1000, 0),
            Arguments.of(new BuyRequest("itemA", 9), 9000, 4000),
            Arguments.of(new BuyRequest("itemB", 2), 3000, 0),
            Arguments.of(new BuyRequest("itemB", 5), 7500, 1500),
            Arguments.of(new BuyRequest("itemB", 8), 12000, 3000));
    }
}