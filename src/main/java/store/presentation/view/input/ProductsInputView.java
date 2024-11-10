package store.presentation.view.input;

import static store.presentation.view.Message.INPUT_PRODUCTS;
import static store.presentation.view.ViewErrors.INVALID_FORMAT;
import static store.presentation.view.ViewErrors.INVALID_INPUT;

import java.util.ArrayList;
import java.util.List;
import store.dto.BuyRequest;
import store.presentation.view.input.abstractview.InputView;

public class ProductsInputView extends InputView<List<BuyRequest>> {

    private static final String PRODUCTS_SEPARATOR = ",";
    private static final String NAME_AMOUNT_SEPARATOR = "-";
    private static final String PATTERN_REGEX = "(\\[[가-힣a-zA-Z0-9]+[-]\\d+\\],?)+";

    @Override
    protected void printLabel(Object... args) {
        System.out.println(INPUT_PRODUCTS.get());
    }

    @Override
    protected List<BuyRequest> convert(String input) {
        validateFormat(input);

        return convertToBuyRequests(input);
    }

    private List<BuyRequest> convertToBuyRequests(String input) {
        List<BuyRequest> buyRequests = new ArrayList<>();

        String[] products = input.split(PRODUCTS_SEPARATOR);
        for (String product : products) {
            BuyRequest buyRequest = mapToBuyRequest(product);
            buyRequests.add(buyRequest);
        }

        return buyRequests;
    }

    private BuyRequest mapToBuyRequest(String inputProduct) {
        inputProduct = inputProduct.substring(1, inputProduct.length() - 1);

        String[] split = inputProduct.split(NAME_AMOUNT_SEPARATOR);
        String productName = split[0];
        int quantity = parseQuantity(split);

        return new BuyRequest(productName, quantity);
    }

    private static int parseQuantity(String[] split) {
        int quantity = Integer.parseInt(split[1]);
        if (quantity <= 0) {
            throw new IllegalArgumentException(INVALID_INPUT.message());
        }
        return quantity;
    }

    protected void validateFormat(String input) {
        if (!input.matches(PATTERN_REGEX)) {
            throw new IllegalArgumentException(INVALID_FORMAT.message());
        }
    }
}
