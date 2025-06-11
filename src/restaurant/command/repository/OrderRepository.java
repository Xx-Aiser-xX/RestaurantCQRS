package restaurant.command.repository;

import restaurant.command.model.Order;
import restaurant.common.exception.OrderNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OrderRepository {
    private final Map<String, Order> orders = new HashMap<>();

    public void save(Order order) {
        orders.put(order.getId(), order);
    }

    public Order findById(String id) {
        return Optional.ofNullable(orders.get(id))
                .orElseThrow(() -> new OrderNotFoundException("заказ не найден: " + id));
    }
}
