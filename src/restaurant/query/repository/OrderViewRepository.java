package restaurant.query.repository;

import restaurant.common.exception.OrderNotFoundException;
import restaurant.query.model.OrderView;

import java.util.*;

public class OrderViewRepository {
    private final Map<String, OrderView> orders = new HashMap<>();

    public void save(OrderView orderView) {
        orders.put(orderView.getId(), orderView);
    }

    public OrderView findById(String id) {
        return Optional.ofNullable(orders.get(id))
                .orElseThrow(() -> new OrderNotFoundException("заказ не найден: " + id));
    }

    public List<OrderView> findAll() {
        return new ArrayList<>(orders.values());
    }
}
