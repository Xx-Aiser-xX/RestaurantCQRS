package restaurant.common.event;

public class FoodAddToOrderEvent extends Event {
    private final String orderId;
    private final String foodId;
    private final String foodName;
    private final double foodPrice;
    private final int quantity;

    public FoodAddToOrderEvent(String orderId, String foodId, String foodName, double foodPrice, int quantity) {
        super();
        this.orderId = orderId;
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.quantity = quantity;
    }

    public String getOrderId() {
        return orderId;
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
