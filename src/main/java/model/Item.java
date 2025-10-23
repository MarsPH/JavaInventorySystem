package model;
public class Item {
   // private int itemid;
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

    public Item(String name) {
        // null check with clear message
        java.util.Objects.requireNonNull(name, "name must not be null"); // throws NPE if null
        String trimmed = name.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("name must not be empty or whitespace");
        }
        if (trimmed.length() > 50) { // choose a limit suitable for the schema
            throw new IllegalArgumentException("name must be at most 50 characters");
        }
        if (!trimmed.matches("[A-Za-z0-9 '._-]+")) { // allow letters, digits, space, and a few symbols
            throw new IllegalArgumentException("name contains invalid characters");
        }
        this.name = trimmed;
    }

    public String getName() {
        return name;
    }
     public Item( String name, int value){
        this(name ,"misc",value);
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
