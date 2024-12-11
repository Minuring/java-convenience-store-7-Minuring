package store;

import java.io.IOException;
import java.util.List;
import store.loader.ProductsLoader;
import store.loader.PromotionsLoader;
import store.order.OrderCart;
import store.order.OrderCart.OrderProduct;
import store.order.OrderSimulator;
import store.order.Receipt;
import store.product.Product;
import store.promotion.Promotion;
import store.view.AddOneInputView;
import store.view.MembershipInputView;
import store.view.NotAppliedPartlyInputView;
import store.view.OrderInputView;
import store.view.OutputView;

public class Application {

    public static void main(String[] args) {
        Store store = createStore();
        OrderSimulator simulator = store.getSimulator();

        while (true) {
            OutputView.printProducts(store.getProducts());

            OrderCart cart = acceptOrderInput(simulator);
            List<OrderProduct> orderProducts = cart.getProducts();

            addOneIfPromotionApplicable(orderProducts, simulator);
            if (checkPromotionNotApplicablePartly(orderProducts, simulator)) {
                Receipt receipt = store.order(cart);
                checkAppliesMembership(receipt);

                OutputView.printReceipt(receipt);
            }

        }
    }

    private static void checkAppliesMembership(Receipt receipt) {
        boolean apply = new MembershipInputView().readUntilSuccess();
        if (apply) {
            receipt.applyMembership();
        }
    }

    private static boolean checkPromotionNotApplicablePartly(
        List<OrderProduct> orderProducts,
        OrderSimulator simulator) {
        for (OrderProduct orderProduct : orderProducts) {
            int partlyNotApplicable = simulator.calculateNotApplicablePromotionPartly(orderProduct);
            if (partlyNotApplicable > 0) {
                boolean confirmed = new NotAppliedPartlyInputView()
                    .readUntilSuccess(orderProduct.name(), partlyNotApplicable);
                if (!confirmed) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void addOneIfPromotionApplicable(List<OrderProduct> orderProducts,
        OrderSimulator simulator) {
        for (OrderProduct orderProduct : orderProducts) {
            if (simulator.canGetFreeIfAddOne(orderProduct)) {
                boolean confirmed = new AddOneInputView().readUntilSuccess(orderProduct.name(), 1);
                if (confirmed) {
                    orderProduct.increaseQuantity();
                }
            }
        }
    }

    private static OrderCart acceptOrderInput(OrderSimulator simulator) {
        while (true) {
            OrderCart cart = new OrderInputView().readUntilSuccess();
            if (simulator.hasAllEnoughStock(cart)) {
                return cart;
            }
            OutputView.printErrorMessage("재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
        }
    }

    private static Store createStore() {
        try {
            return buildStore();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private static Store buildStore() throws IOException {
        List<Promotion> promotions = new PromotionsLoader(
            "src/main/resources/promotions.md"
        ).read();

        List<Product> products = new ProductsLoader(
            "src/main/resources/products.md", promotions
        ).read();

        return new Store(promotions, products);
    }
}
