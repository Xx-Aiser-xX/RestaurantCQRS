package restaurant.command.handler;

import restaurant.command.command.CreateOrderCommand;
import restaurant.command.model.Order;
import restaurant.command.repository.OrderRepository;

public class CreateOrderCommandHandler implements CommandHandler<CreateOrderCommand> {
    private final OrderRepository orderRepository;

    public CreateOrderCommandHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void handle(CreateOrderCommand command) {
        Order order = new Order(command.getCustomerId());
        orderRepository.save(order);
    }
}
