import restaurant.api.console.ConsoleInterface;
import restaurant.api.facade.OrderFacade;
import restaurant.command.command.AddFoodToOrderCommand;
import restaurant.command.command.CreateOrderCommand;
import restaurant.command.command.RemoveFoodFromOrderCommand;
import restaurant.command.command.UpdateOrderStatusCommand;
import restaurant.command.handler.*;
import restaurant.command.repository.OrderRepository;
import restaurant.common.event.EventBus;
import restaurant.query.repository.OrderViewRepository;
import restaurant.query.service.EventHandler;
import restaurant.query.service.OrderQueryService;

public class Main {
    public static void main(String[] args) {
        OrderRepository orderRepository = new OrderRepository();
        OrderViewRepository orderViewRepository = new OrderViewRepository();

        EventHandler eventHandler = new EventHandler(orderViewRepository);
        EventBus.getInstance().register(eventHandler);

        CommandBus commandBus = new CommandBus();
        commandBus.register(CreateOrderCommand.class, new CreateOrderCommandHandler(orderRepository));
        commandBus.register(AddFoodToOrderCommand.class, new AddFoodToOrderCommandHandler(orderRepository));
        commandBus.register(RemoveFoodFromOrderCommand.class, new RemoveFoodFromOrderCommandHandler(orderRepository));
        commandBus.register(UpdateOrderStatusCommand.class, new UpdateOrderStatusCommandHandler(orderRepository));

        OrderQueryService queryService = new OrderQueryService(orderViewRepository);

        OrderFacade orderFacade = new OrderFacade(commandBus, queryService);

        try {
            orderFacade.createOrder("1");
            String id = orderFacade.getAllOrders().getFirst().getId();
            orderFacade.addFoodToOrder(id, "1", "Пицца", 500, 2);
            orderFacade.addFoodToOrder(id, "2", "Салат", 200, 1);
            System.out.println("тестовые данные успешно созданы");
        } catch (Exception e) {
            System.out.println("ошибка при создании тестовых данных: " + e.getMessage());
        }

        ConsoleInterface ui = new ConsoleInterface(orderFacade);
        ui.start();
    }
}