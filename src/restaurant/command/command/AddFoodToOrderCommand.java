package restaurant.command.command;

import java.util.UUID;

public class AddFoodToOrderCommand implements Command {
    private final String commandId;
    private final String orderId;
    private final String foodId;
    private final String foodName;
    private final double foodPrice;
    private final int quantity;

    public AddFoodToOrderCommand(String orderId, String foodId, String foodName, double foodPrice, int quantity) {
        this.commandId = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.quantity = quantity;
    }

    @Override
    public String getCommandId() {
        return commandId;
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
