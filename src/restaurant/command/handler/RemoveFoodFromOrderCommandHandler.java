package restaurant.command.handler;

import restaurant.command.command.RemoveFoodFromOrderCommand;
import restaurant.command.model.Order;
import restaurant.command.repository.OrderRepository;

public class RemoveFoodFromOrderCommandHandler implements CommandHandler<RemoveFoodFromOrderCommand> {
    private final OrderRepository orderRepository;

    public RemoveFoodFromOrderCommandHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void handle(RemoveFoodFromOrderCommand command) {
        Order order = orderRepository.findById(command.getOrderId());
        order.removeFood(command.getOrderId(), command.getFoodId());
        orderRepository.save(order);
    }
}
