package store.view;

import java.util.List;
import store.order.Receipt;
import store.order.Receipt.PurchasedProduct;
import store.product.Products;

public class OutputView {

    public static void printErrorMessage(String message) {
        System.out.println("[ERROR] " + message);
    }

    public static void printProducts(Products products) {
        System.out.println("안녕하세요. W 편의점입니다.");
        System.out.println("현재 보유하고 있는 상품입니다.");
        System.out.println();
        System.out.println(products);
        System.out.println();
    }

    public static void printReceipt(Receipt receipt) {
        List<PurchasedProduct> purchasedProducts = receipt.getPurchasedProducts();
        System.out.println("==========W 편의점=========");
        System.out.println("상품명\t\t수량\t\t금액");
        purchasedProducts.forEach(p ->
            System.out.printf("%s %d %,d\n", p.getName(), p.getOrderQuantity(), p.getOrderQuantity() * p.getOrderPrice()));

        System.out.println("==========증\t정=========");
        purchasedProducts.stream()
            .filter(p -> p.getFree() > 0)
            .forEach(p -> System.out.printf("%s %d\n", p.getName(), p.getFree()));
        System.out.println("========================");

        System.out.printf("총구매액\t%d\t%,d\n", receipt.getTotalOrderQuantity(),
            receipt.getTotalOrderPrice());
        System.out.printf("행사할인\t-%,d\n", receipt.getTotalPromotionDiscount());
        System.out.printf("멤버십할인\t-%,d\n", receipt.getMembershipDiscount());
        System.out.printf("내실돈\t%,d\n", receipt.getActualPay());
        System.out.println();
    }
}