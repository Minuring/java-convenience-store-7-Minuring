package store.presentation.view.output;

import static store.presentation.view.Message.PRODUCTS_HEADER;

import java.util.List;
import store.item.domain.Item;
import store.item.inventory.Inventory;
import store.presentation.view.output.abstractview.ArgumentOutputView;

public class ProductsOutputView extends ArgumentOutputView<Inventory> {

    @Override
    protected void printHeader() {
        System.out.println(PRODUCTS_HEADER.get() + System.lineSeparator());
    }

    @Override
    protected void printBody(Inventory target) {
        List<Item> allItems = target.getAllItems();
        allItems.stream()
            .sorted()
            .forEach(System.out::println);
    }
}
