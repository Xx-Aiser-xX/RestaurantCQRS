package restaurant.command.handler;

import restaurant.command.command.UpdateOrderStatusCommand;
import restaurant.command.model.Order;
import restaurant.command.repository.OrderRepository;

public class UpdateOrderStatusCommandHandler implements CommandHandler<UpdateOrderStatusCommand> {
    private final OrderRepository orderRepository;

    public UpdateOrderStatusCommandHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void handle(UpdateOrderStatusCommand command) {
        Order order = orderRepository.findById(command.getOrderId());
        order.updateStatus(command.getNewStatus());
        orderRepository.save(order);
    }
}
