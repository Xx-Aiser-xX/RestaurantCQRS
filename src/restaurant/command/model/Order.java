package restaurant.command.model;

import restaurant.common.event.FoodAddToOrderEvent;
import restaurant.common.event.FoodRemovedFromOrderEvent;
import restaurant.common.event.EventBus;
import restaurant.common.event.OrderCreatedEvent;
import restaurant.common.event.OrderStatusUpdatedEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Order {
    private final String id;
    private final String customerId;
    private final List<Food> foods;
    private OrderStatus status;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Order(String customerId) {
        this.id = UUID.randomUUID().toString();
        this.customerId = customerId;
        this.foods = new ArrayList<>();
        this.status = OrderStatus.CREATE;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
        EventBus.getInstance().publish(new OrderCreatedEvent(id, customerId));
    }

    public void addFood(String id, String name, double price, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("кол-во должно быть >=0");
        }
        Optional<Food> temp = Optional.empty();
        for (Food food : foods){
            if(food.getId().equals(id))
                temp = Optional.of(food);
        }
        if (temp.isPresent()) {
            temp.get().incrQuantity(quantity);
        } else {
            foods.add(new Food(id, quantity));
        }
        updatedAt = LocalDateTime.now();
        EventBus.getInstance().publish(new FoodAddToOrderEvent(this.id, id, name, price, quantity));
    }

    public void removeFood(String id, String foodId) {
        for(int i = 0; i < foods.size(); i++){
            if(foods.get(i).getId().equals(id))
                foods.remove(i);
        }
        updatedAt = LocalDateTime.now();
        EventBus.getInstance().publish(new FoodRemovedFromOrderEvent(id, foodId));
    }

    public void updateStatus(OrderStatus newStatus) {
        status = newStatus;
        updatedAt = LocalDateTime.now();
        EventBus.getInstance().publish(new OrderStatusUpdatedEvent(id, newStatus));
    }

    public String getId() {
        return id;
    }
    public String getCustomerId() {
        return customerId;
    }
    public List<Food> getFoods() {
        return new ArrayList<>(foods);
    }
    public OrderStatus getStatus() {
        return status;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", foods=" + foods +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}