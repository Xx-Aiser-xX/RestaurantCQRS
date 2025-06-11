package restaurant.api.facade;

import restaurant.command.command.AddFoodToOrderCommand;
import restaurant.command.command.CreateOrderCommand;
import restaurant.command.command.RemoveFoodFromOrderCommand;
import restaurant.command.command.UpdateOrderStatusCommand;
import restaurant.command.handler.CommandBus;
import restaurant.command.model.OrderStatus;
import restaurant.query.dto.FoodDTO;
import restaurant.query.dto.OrderDTO;
import restaurant.query.service.OrderQueryService;

import java.util.List;

public class OrderFacade {
    private final CommandBus commandBus;
    private final OrderQueryService queryService;

    public OrderFacade(CommandBus commandBus, OrderQueryService queryService) {
        this.commandBus = commandBus;
        this.queryService = queryService;
    }

    public void createOrder(String customerId) {
        commandBus.dispatch(new CreateOrderCommand(customerId));
    }

    public void addFoodToOrder(String id, String foodId, String name, double price, int quantity) {
        commandBus.dispatch(new AddFoodToOrderCommand(id, foodId, name, price, quantity));
    }

    public void removeFoodFromOrder(String id, String foodId) {
        commandBus.dispatch(new RemoveFoodFromOrderCommand(id, foodId));
    }

    public void updateOrderStatus(String id, OrderStatus newStatus) {
        commandBus.dispatch(new UpdateOrderStatusCommand(id, newStatus));
    }

    public OrderDTO getOrder(String id) {
        return queryService.getOrderById(id);
    }

    public List<OrderDTO> getAllOrders() {
        return queryService.getAllOrders();
    }

    public List<FoodDTO> getFoodsInOrder(String id) {
        return queryService.getFoodById(id);
    }

    public List<OrderDTO> getHistoryOrders() {
        return queryService.historyOrder();
    }
}
