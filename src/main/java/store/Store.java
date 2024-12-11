package store;

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
            appliedQuantity = promotion.countAppliedQuantity(quantity, product.getStock());
            free = promotion.countFreeWithStock(quantity, product.getStock());
            discount = free * price;
        }
        receipt.add(name, quantity, price * quantity, appliedQuantity, free, discount);
    }

    private void takeStocks(OrderProduct orderProduct) {
        int remainingQuantity = orderProduct.quantity();
        String name = orderProduct.name();

        if (products.hasPromotionItemByName(name)) {
            Product product = products.getPromotionProductByName(name);
            Promotion promotion = product.getPromotion().get();
            if (promotion.isApplicable(LocalDate.now())) {
                int promotionUsage = takePromotionStock(name, remainingQuantity);
                remainingQuantity -= promotionUsage;
            }
        }
        takeNormalStock(name, remainingQuantity);
    }

    private int takePromotionStock(String name, int remainingQuantity) {
        Product product = products.getPromotionProductByName(name);
        product.takeStock(remainingQuantity);
        return product.getStock();
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
        onlyPromotionProducts.forEach(this.products::addProduct);
    }
}
