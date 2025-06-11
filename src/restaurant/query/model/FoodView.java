package restaurant.query.model;

public class FoodView {
    private String foodId;
    private String foodName;
    private double foodPrice;
    private int quantity;

    public FoodView(String foodId, String foodName, double foodPrice, int quantity) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.quantity = quantity;
    }

    public String getFoodId() {
        return foodId;
    }
    public String getFoodName() {
        return foodName;
    }
    public double getFoodPrice() {
        return foodPrice;
    }
    public int getQuantity() {
        return quantity;
    }
}
