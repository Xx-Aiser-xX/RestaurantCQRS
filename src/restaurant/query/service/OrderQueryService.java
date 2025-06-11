package restaurant.query.service;

import restaurant.query.dto.OrderDTO;
import restaurant.query.dto.FoodDTO;
import restaurant.query.model.FoodView;
import restaurant.query.model.OrderView;
import restaurant.query.repository.OrderViewRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderQueryService {
    private final OrderViewRepository orderViewRepository;

    public OrderQueryService(OrderViewRepository orderViewRepository) {
        this.orderViewRepository = orderViewRepository;
    }

    public OrderDTO getOrderById(String orderId) {
        OrderView order = orderViewRepository.findById(orderId);
        return orderInTheDto(order);
    }

    public List<OrderDTO> getAllOrders() {
        List<OrderView> orders = orderViewRepository.findAll();
        List<OrderDTO> result = new ArrayList<>();
        for (OrderView order : orders) {
            result.add(orderInTheDto(order));
        }
        return result;
    }

    public List<FoodDTO> getFoodById(String orderId) {
        OrderView order = orderViewRepository.findById(orderId);
        return foodInTheDto(order);
    }

    public List<OrderDTO> historyOrder(){
        List<OrderView> orders = orderViewRepository.findAll();
        List<OrderDTO> result = new ArrayList<>();
        orders = orders.stream().sorted((o1, o2) -> o1.getCreatedAt().compareTo(o2.getCreatedAt())).toList();
        for (OrderView order : orders) {
            result.add(orderInTheDto(order));
        }
        return result;
    }

    private OrderDTO orderInTheDto(OrderView order) {
        List<FoodDTO> foods = new ArrayList<>();
        for (FoodView temp : order.getFoods()) {
            FoodDTO food = new FoodDTO(temp.getFoodId(), temp.getFoodName(),
                    temp.getFoodPrice(), temp.getQuantity()
            );
            foods.add(food);
        }
        return new OrderDTO(order.getId(), order.getCustomerId(), foods, order.getStatus(),
                order.getCreatedAt(), order.getUpdatedAt(), order.getTotalPrice());
    }

    private List<FoodDTO> foodInTheDto(OrderView order){
        List<FoodDTO> foods = new ArrayList<>();
        for (FoodView temp : order.getFoods()) {
            FoodDTO food = new FoodDTO(temp.getFoodId(), temp.getFoodName(),
                    temp.getFoodPrice(), temp.getQuantity()
            );
            foods.add(food);
        }
        return foods;
    }
}
