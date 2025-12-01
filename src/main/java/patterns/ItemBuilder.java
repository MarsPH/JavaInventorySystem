package patterns;

import model.Item;

public class ItemBuilder {
    private int itemId = 0;
    private String name;
    private int value;
    private String category = "misc";
    private int quantity = 0;

    public ItemBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder value(int value) {
        this.value = value;
        return this;
    }

    public ItemBuilder category(String category) {
        this.category = category;
        return this;
    }

    public ItemBuilder quantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public ItemBuilder itemId(int itemId) {
        this.itemId = itemId;
        return this;
    }

    public Item build() {
        return new Item(itemId, name, category, value, quantity);
    }
}
