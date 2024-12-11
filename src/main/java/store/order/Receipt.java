package store.order;

import java.util.ArrayList;
import java.util.List;

public class Receipt {

    private List<PurchasedProduct> purchasedProducts = new ArrayList<>();
    private int membershipDiscount = 0;

    public void add(String name, int orderQuantity, int orderPrice, int promotionApplied, int free,
        int discountAmount) {
        PurchasedProduct purchasedProduct = new PurchasedProduct(
            name, orderQuantity, orderPrice, promotionApplied, free, discountAmount);

        purchasedProducts.add(purchasedProduct);
    }

    public void add(String name, int orderQuantity, int orderPrice) {
        this.add(name, orderQuantity, orderPrice, 0, 0, 0);
    }

    public List<PurchasedProduct> getPurchasedProducts() {
        return purchasedProducts;
    }

    public int getTotalOrderPrice() {
        return purchasedProducts.stream()
            .mapToInt(p -> p.getOrderPrice() * p.getOrderQuantity())
            .sum();
    }

    public int getTotalOrderQuantity() {
        return purchasedProducts.stream()
            .mapToInt(PurchasedProduct::getOrderQuantity)
            .sum();
    }

    public int getTotalPromotionDiscount() {
        return purchasedProducts.stream()
            .mapToInt(PurchasedProduct::getDiscountPrice)
            .sum();
    }

    public int getTotalPromotionAppliedPrice() {
        return purchasedProducts.stream()
            .mapToInt(p -> p.promotionApplied * p.orderPrice)
            .sum();
    }

    public void applyMembership() {
        int notAppliedPrice = getTotalOrderPrice() - getTotalPromotionAppliedPrice();
        membershipDiscount = Math.min((int) (notAppliedPrice * 0.3), 8000);
    }

    public int getMembershipDiscount() {
        return membershipDiscount;
    }

    public int getActualPay() {
        return getTotalOrderPrice() - getTotalPromotionDiscount() - getMembershipDiscount();
    }

    public static class PurchasedProduct {

        private final String name;
        private final int orderQuantity;
        private final int orderPrice;

        private final int promotionApplied;
        private final int free;
        private final int discountPrice;

        public PurchasedProduct(String name, int orderQuantity, int orderPrice,
            int promotionApplied,
            int free, int discountPrice) {
            this.name = name;
            this.orderQuantity = orderQuantity;
            this.orderPrice = orderPrice;
            this.promotionApplied = promotionApplied;
            this.free = free;
            this.discountPrice = discountPrice;
        }

        public String getName() {
            return name;
        }

        public int getOrderQuantity() {
            return orderQuantity;
        }

        public int getOrderPrice() {
            return orderPrice;
        }

        public int getPromotionApplied() {
            return promotionApplied;
        }

        public int getFree() {
            return free;
        }

        public int getDiscountPrice() {
            return discountPrice;
        }
    }

}
