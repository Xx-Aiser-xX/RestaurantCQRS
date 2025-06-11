package restaurant.command.handler;

import restaurant.command.command.AddFoodToOrderCommand;
import restaurant.command.model.Order;
import restaurant.command.repository.OrderRepository;

public class AddFoodToOrderCommandHandler implements CommandHandler<AddFoodToOrderCommand> {
    private final OrderRepository orderRepository;

    public AddFoodToOrderCommandHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void handle(AddFoodToOrderCommand command) {
        Order order = orderRepository.findById(command.getOrderId());
        order.addFood(command.getFoodId(), command.getFoodName(), command.getFoodPrice(), command.getQuantity());
        orderRepository.save(order);
    }
}
