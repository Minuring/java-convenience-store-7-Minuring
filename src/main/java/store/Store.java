package store;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import store.order.OrderCart;
import store.order.OrderCart.OrderProduct;
import store.order.OrderSimulator;
import store.order.Receipt;
import store.product.Product;
import store.product.Products;
import store.promotion.Promotion;
import store.promotion.Promotions;

public class Store {

    private final Promotions promotions;
    private final Products products;
    private final OrderSimulator simulator;

    public Store(List<Promotion> promotions, List<Product> products) {
        this.promotions = new Promotions(promotions);
        this.products = new Products(products);

        addAllProductsThatOnlyPromotionProduct(products);
        this.simulator = new OrderSimulator(this.products, this.promotions);
    }

    public Receipt order(OrderCart cart) {
        List<OrderProduct> orderProducts = cart.getProducts();
        Receipt receipt = new Receipt();
        for (OrderProduct orderProduct : orderProducts) {
            saveToReceipt(orderProduct, receipt);
            takeStocks(orderProduct);
        }
        return receipt;
    }

    private void saveToReceipt(OrderProduct orderProduct, Receipt receipt) {
        String name = orderProduct.name();
        int quantity = orderProduct.quantity();
        int price = products.getNormalProductByName(name).getPrice();
        int appliedQuantity = 0;
        int free = 0;
        int discount = 0;
        if (products.hasPromotionItemByName(name)) {
            Product product = products.getPromotionProductByName(name);
            Promotion promotion = product.getPromotion().get();
            if (promotion.isApplicable(DateTimes.now().toLocalDate())) {
                appliedQuantity = promotion.countAppliedQuantity(quantity, product.getStock());
                free = promotion.countFreeWithStock(quantity, product.getStock());
                discount = free * price;
            }
        }
        receipt.add(name, quantity, price, appliedQuantity, free, discount);
    }

    private void takeStocks(OrderProduct orderProduct) {
        int remainingQuantity = orderProduct.quantity();
        String name = orderProduct.name();

        if (products.hasPromotionItemByName(name)) {
            Product product = products.getPromotionProductByName(name);
            Promotion promotion = product.getPromotion().get();
            if (promotion.isApplicable(DateTimes.now().toLocalDate())) {
                int promotionUsage = takePromotionStock(name, remainingQuantity);
                remainingQuantity -= promotionUsage;
            }
        }
        System.out.println("remainingQuantity = " + remainingQuantity);
        takeNormalStock(name, remainingQuantity);
    }

    private int takePromotionStock(String name, int remainingQuantity) {
        Product product = products.getPromotionProductByName(name);
        int usage = Math.min(remainingQuantity, product.getStock());
        product.takeStock(usage);
        return usage;
    }

    private void takeNormalStock(String name, int remainingQuantity) {
        Product product = products.getNormalProductByName(name);
        product.takeStock(remainingQuantity);
    }

    public Products getProducts() {
        return products;
    }

    public OrderSimulator getSimulator() {
        return simulator;
    }

    private void addAllProductsThatOnlyPromotionProduct(List<Product> products) {
        List<Product> onlyPromotionProducts = products.stream()
            .filter(Product::isPromotion)
            .map(Product::getName)
            .map(this.products::findByName)
            .filter(list -> list.size() == 1)
            .flatMap(Collection::stream)
            .toList();
        onlyPromotionProducts.forEach(p -> {
            Product copyNormal = Product.of(p.getName(), p.getPrice(), 0);
            this.products.addProduct(copyNormal);
        });
    }
}
