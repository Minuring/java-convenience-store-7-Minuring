package store.order.service;

import static camp.nextstep.edu.missionutils.DateTimes.now;
import static store.constant.ConstantNumbers.EXACT_GET_PROMOTION;

import java.util.List;
import store.dto.BuyRequest;
import store.facade.ExceptionFacade;
import store.item.domain.NormalItem;
import store.item.domain.PromotionItem;
import store.item.exception.ItemNotFoundException;
import store.item.exception.NotEnoughStockException;
import store.item.inventory.Inventory;
import store.order.domain.Order;
import store.order.domain.OrderItem;
import store.order.exception.OrderCanceledException;

public class OrderServiceImpl implements OrderService {

    private final Inventory inventory;
    private final ConfirmListener<String, Integer> appendOneListener;
    private final ConfirmListener<String, Integer> regularPriceListener;

    public OrderServiceImpl(Inventory inventory,
        ConfirmListener<String, Integer> appendOneListener,
        ConfirmListener<String, Integer> regularPriceListener) {
        this.inventory = inventory;
        this.appendOneListener = appendOneListener;
        this.regularPriceListener = regularPriceListener;
    }

    @Override
    public Order order(List<BuyRequest> buyRequests) {
        throwIfItemNotFound(buyRequests);
        throwIfStockNotEnough(buyRequests);
        Order order = new Order();

        buyRequests.stream()
            .map(this::orderEachRequest)
            .forEachOrdered(order::addOrderItem);

        return order;
    }

    private OrderItem orderEachRequest(BuyRequest buyRequest) {
        OrderItem orderItem = readyOrderItem(buyRequest);

        processPromotionStock(buyRequest, orderItem);
        processNormalStock(buyRequest, orderItem);

        return orderItem;
    }

    private void processPromotionStock(BuyRequest buyRequest, OrderItem orderItem) {
        inventory.findPromotionItemByName(buyRequest.itemName())
            .ifPresent(item -> {
                int promotionUsage = takeoutPromotionStock(buyRequest, item);
                int freeCount = getFreeCount(item, promotionUsage);
                orderItem.addQuantity(promotionUsage);
                orderItem.setPromotionApplied(item.countTakeToGetFree(freeCount));
                orderItem.setFree(freeCount);
            });
    }

    private int takeoutPromotionStock(BuyRequest buyRequest, PromotionItem item) {
        int promotionUsage = determinePromotionUsage(buyRequest.amount(), item);
        int free = item.countFreeOnTake(promotionUsage);

        checkRegularPricePay(
            buyRequest.amount() - item.countTakeToGetFree(free),
            item.getStock() - promotionUsage,
            buyRequest.itemName());
        item.removeStock(promotionUsage);
        return promotionUsage;
    }

    private int determinePromotionUsage(int buyAmount, PromotionItem promotionItem) {
        int usage = Math.min(promotionItem.getStock(), buyAmount);

        if (canGetFreeIfAppend(buyAmount, promotionItem)
            && ExceptionFacade.process(() ->
            appendOneListener.apply(promotionItem.getName(), EXACT_GET_PROMOTION.get()))) {
            usage += EXACT_GET_PROMOTION.get();
        }
        return usage;
    }

    private boolean canGetFreeIfAppend(int amount, PromotionItem promotionItem) {
        return promotionItem.canApplyAt(now())
            && promotionItem.countRemainsToApply(amount) == EXACT_GET_PROMOTION.get()
            && promotionItem.getStock() >= amount + EXACT_GET_PROMOTION.get();
    }

    private void processNormalStock(BuyRequest buyRequest, OrderItem orderItem) {
        inventory.findNormalItemByName(buyRequest.itemName())
            .ifPresent(item -> {
                int normalUsage = Math.max(buyRequest.amount() - orderItem.getQuantity(), 0);
                item.removeStock(normalUsage);
                orderItem.addQuantity(normalUsage);
            });
    }

    private void checkRegularPricePay(int remainingBuyAmount, int remainingPromotionStock,
        String itemName) {
        if (remainingBuyAmount <= remainingPromotionStock) {
            return;
        }

        if (!ExceptionFacade.process(() ->
            regularPriceListener.apply(itemName, remainingBuyAmount))) {
            throw new OrderCanceledException();
        }
    }

    private int getFreeCount(PromotionItem promotionItem, int promotionUsage) {
        if (!promotionItem.canApplyAt(now())) {
            return 0;
        }

        return promotionItem.countFreeOnTake(promotionUsage);
    }

    private OrderItem readyOrderItem(BuyRequest buyRequest) {
        String name = buyRequest.itemName();
        int price = inventory.findPromotionItemByName(name).map(PromotionItem::getPrice).orElse(
            inventory.findNormalItemByName(name).map(NormalItem::getPrice).orElse(0)
        );

        return new OrderItem(name, price);
    }

    private void throwIfItemNotFound(List<BuyRequest> buyRequests) {
        buyRequests.stream()
            .map(BuyRequest::itemName)
            .filter(inventory::hasItemByName)
            .findAny()
            .orElseThrow(ItemNotFoundException::new);
    }

    private void throwIfStockNotEnough(List<BuyRequest> buyRequests) {
        for (BuyRequest request : buyRequests) {
            String name = request.itemName();
            int amount = request.amount();

            if (inventory.getTotalStockByName(name) < amount) {
                throw new NotEnoughStockException();
            }
        }
    }
}
