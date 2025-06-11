package restaurant.command.model;

public class Food {
    private final String id;
    private int quantity;

    public Food(String id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
    public void incrQuantity(int amount) {
        this.quantity += amount;
    }
    public String getId() {
        return id;
    }
}