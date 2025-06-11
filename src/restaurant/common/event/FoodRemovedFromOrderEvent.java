package restaurant.common.event;

public class FoodRemovedFromOrderEvent extends Event {
    private final String orderId;
    private final String foodId;

    public FoodRemovedFromOrderEvent(String orderId, String foodId) {
        super();
        this.orderId = orderId;
        this.foodId = foodId;
    }

    public String getOrderId() {
        return orderId;
    }
    public String getFoodId() {
        return foodId;
    }
}
