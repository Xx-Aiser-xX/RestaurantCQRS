package restaurant.command.command;

import java.util.UUID;

public class RemoveFoodFromOrderCommand implements Command {
    private final String commandId;
    private final String orderId;
    private final String foodId;

    public RemoveFoodFromOrderCommand(String orderId, String foodId) {
        this.commandId = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.foodId = foodId;
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
}
