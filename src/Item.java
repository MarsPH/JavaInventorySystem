public class Item {

    public String name;
    public int Value;
    private final int minValue = 0;
    private final int maxValue = 100;

    public Item(String name, int value) throws IllegalArgumentException {
        if (name.isEmpty() || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be empty");
        }

        if (value < minValue || value > maxValue) {
            throw new IllegalArgumentException("Item value must be between " + minValue + " and " + maxValue);
        }
        this.name = name;
        this.Value = value;
    }

    public String getName() {
        return name;
    }
    public int getValue() {
        return Value;
    }

}
