package store.loader;

import java.util.List;
import java.util.regex.Pattern;
import store.product.Product;
import store.promotion.Promotion;

public class ProductsLoader extends FileLoader<Product> {

    private static final Pattern PATTERN = Pattern.compile("^\\S+,\\d+,\\d+,\\S+$");
    private final List<Promotion> promotions;

    public ProductsLoader(String path, List<Promotion> promotions) {
        super(path);
        this.promotions = promotions;
    }

    @Override
    protected Product bindLine(String line) {
        validateLine(line);
        String[] elements = line.split(",");

        String name = elements[0];
        int price = Integer.parseInt(elements[1]);
        int stock = Integer.parseInt(elements[2]);
        Promotion promotion = getPromotionByName(elements[3]);

        return Product.of(name, price, stock, promotion);
    }

    private Promotion getPromotionByName(String name) {
        return promotions.stream()
            .filter(promotion -> promotion.hasName(name))
            .findFirst()
            .orElse(null);
    }

    private void validateLine(String line) {
        boolean matches = PATTERN.matcher(line).matches();
        if (!matches) {
            throw new InvalidFileFormatException();
        }
    }
}
