package store.order.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import store.dto.BuyRequest;
import store.item.inventory.Inventory;
import store.mock.ServiceTestInventory;

public class OrderServiceImplViewTest {

    private String calledMethodNames = "";
    private final RequiredConfirmListener<String, Integer> confirmAppendItem = (s, i) -> {
        calledMethodNames += "confirmAppendItem";
        return true;
    };
    private final RequiredConfirmListener<String, Integer> confirmRegularPrice = (s, i) -> {
        calledMethodNames += "confirmRegularPrice";
        return true;
    };

    private Inventory inventory;
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        inventory = new ServiceTestInventory();
        orderService = new OrderServiceImpl(inventory, confirmAppendItem, confirmRegularPrice);
    }

    @ParameterizedTest
    @MethodSource("provideBuysOneLess")
    void 하나_추가시_프로모션_적용_가능할때_확인요구가_호출된다(BuyRequest buyRequest) throws Exception {
        orderService.order(List.of(buyRequest));
        assertThat(calledMethodNames).contains("confirmAppendItem");
    }

    private static Stream<Arguments> provideBuysOneLess() {
        return Stream.of(
            Arguments.of(new BuyRequest("itemA", 1)),
            Arguments.of(new BuyRequest("itemA", 9)),
            Arguments.of(new BuyRequest("itemB", 2)),
            Arguments.of(new BuyRequest("itemB", 5)),
            Arguments.of(new BuyRequest("itemB", 8)));
    }

    @ParameterizedTest
    @MethodSource("provideBuysExceedStock")
    void 하나_추가하면_받을수있지만_프로모션재고가_부족하면_확인요구가_호출되지_않는다(BuyRequest buyRequest) throws Exception {
        orderService.order(List.of(buyRequest));
        assertThat(calledMethodNames).doesNotContain("confirmAppendItem");
    }

    private static Stream<Arguments> provideBuysExceedStock() {
        return Stream.of(
            Arguments.of(new BuyRequest("itemA", 11)),
            Arguments.of(new BuyRequest("itemB", 11)),
            Arguments.of(new BuyRequest("itemD", 1)));
    }

    @Test
    void 프로모션기간이_종료되었으면_증정수량을_만족할지라도_추가할지_확인을_하지않는다() throws Exception {
        orderService.order(List.of(new BuyRequest("itemC", 1)));
        assertThat(calledMethodNames).doesNotContain("confirmAppendItem");
    }

    @ParameterizedTest
    @MethodSource("provideBuysRegularPriceInStock")
    void 프로모션단위와_맞아떨어지지_않으면_정가결제_확인을_받는다(BuyRequest buyRequest) throws Exception {
        orderService.order(List.of(buyRequest));
        assertThat(calledMethodNames).contains("confirmRegularPrice");
    }

    private static Stream<Arguments> provideBuysRegularPriceInStock() {
        return Stream.of(
            Arguments.of(new BuyRequest("itemA", 11)),
            Arguments.of(new BuyRequest("itemB", 7)),
            Arguments.of(new BuyRequest("itemB", 10)),
            Arguments.of(new BuyRequest("itemB", 11))
        );
    }

    @Test
    void 프로모션기간이_종료되었으면_증정수량을_만족할지라도_정가결제_확인을_받는다() throws Exception {
        orderService.order(List.of(new BuyRequest("itemC", 1)));
        assertThat(calledMethodNames).contains("confirmRegularPrice");
    }

    @Test
    void 프로모션재고가_없으면_정가결제_확인을_받지않는다() throws Exception {
        orderService.order(List.of(new BuyRequest("itemD", 1)));
        assertThat(calledMethodNames).doesNotContain("confirmAppendItem");
    }
}
