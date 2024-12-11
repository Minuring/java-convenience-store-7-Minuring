package store.view;

import java.util.List;
import store.order.Receipt;
import store.order.Receipt.PurchasedProduct;

public class OutputView {

    public static void printErrorMessage(String message) {
        System.out.println("[ERROR] " + message);
    }

    public static void printReceipt(Receipt receipt) {
        List<PurchasedProduct> purchasedProducts = receipt.getPurchasedProducts();
        System.out.println("==========W 편의점=========");
        System.out.println("상품명\t\t수량\t\t금액");
        purchasedProducts.forEach(p ->
            System.out.printf("%s %d %,d\n", p.getName(), p.getOrderQuantity(), p.getOrderPrice()));

        System.out.println("==========증\t정=========");
        purchasedProducts.stream()
            .filter(p -> p.getFree() > 0)
            .forEach(p -> System.out.printf("%s %d\n", p.getName(), p.getFree()));
        System.out.println("========================");

        System.out.printf("총구매액\t%d\t%,d\n", receipt.getTotalOrderQuantity(),
            receipt.getTotalOrderPrice());
        System.out.printf("행사할인\t-%,d\n", receipt.getTotalPromotionDiscount());
        System.out.printf("멤버십할인\t-%,d\n", receipt.getMembershipDiscount());
        System.out.printf("내실돈\t%,d", receipt.getActualPay());
    }
}

/*
구매 상품 내역 , 증정 상품 내역 , 금액 정보를 출력한다.
=W 편의점 ==: ニ ニ ニ ニ ニ ニ ニ ニ ニ
상품명 수량 금액
콜라 3 3,000
에너지바 5 10,000
ニ ニ ニ ニ ニ ニ == 증 정=
콜라 1
ニ ニ ニ ニ ニ
총구매액 8 13,000
행사할인 -1,000
멤버십할인 -3,000
내실돈 9,000
 */