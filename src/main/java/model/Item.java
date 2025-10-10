package model;
public class Item {

    private String name;
    private int Value;
    private String Category;
    private final int minValue = 0;
    private final int maxValue = 100;

    public Item(String name, String category, int value) throws IllegalArgumentException {
        if (name.isEmpty() || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be empty");
        }

        if (value < minValue || value > maxValue) {
            throw new IllegalArgumentException("Item value must be between " + minValue + " and " + maxValue);
        }
        this.name = name;
        this.Value = value;
        this.Category =category;
    }

    public String getName() {
        return name;
    }
    public int getValue() {
        return Value;
    }

    public String getCategory() {
        return Category;
    }

    @Override
    public String toString() {
        return name + " (" + Category + ") - " + Value + " gold";
    }

}
