package store.dto;

public class BuyRequest {

    private String itemName;
    private int amount;

    public BuyRequest(String itemName, int amount) {
        this.itemName = itemName;
        this.amount = amount;
    }

    public String getItemName() {
        return itemName;
    }

    public int getAmount() {
        return amount;
    }
}
