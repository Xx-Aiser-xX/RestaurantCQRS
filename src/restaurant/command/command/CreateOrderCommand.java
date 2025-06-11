package restaurant.command.command;

import java.util.UUID;

public class CreateOrderCommand implements Command {
    private final String commandId;
    private final String customerId;

    public CreateOrderCommand(String customerId) {
        this.commandId = UUID.randomUUID().toString();
        this.customerId = customerId;
    }

    @Override
    public String getCommandId() {
        return commandId;
    }
    public String getCustomerId() {
        return customerId;
    }
}
