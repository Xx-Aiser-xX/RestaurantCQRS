package restaurant.query.model;

import restaurant.command.model.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderView {
    private String id;
    private String customerId;
    private List<FoodView> foods;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private double totalPrice;

    public OrderView(String id, String customerId, LocalDateTime createdAt) {
        this.id = id;
        this.customerId = customerId;
        this.foods = new ArrayList<>();
        this.status = OrderStatus.CREATE;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
        this.totalPrice = 0;
    }

    public void addFood(String id, String foodName, double foodPrice, int quantity) {
        foods.add(new FoodView(id, foodName, foodPrice, quantity));
        totalPrice += foodPrice * quantity;
        updatedAt = LocalDateTime.now();
    }

    public void removeFood(String foodId) {
        for (FoodView item : foods) {
            if (item.getFoodId().equals(foodId)) {
                totalPrice -= item.getFoodPrice() * item.getQuantity();
                foods.remove(item);
                updatedAt = LocalDateTime.now();
                break;
            }
        }
    }

    public void updateStatus(OrderStatus newStatus) {
        this.status = newStatus;
        updatedAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }
    public String getCustomerId() {
        return customerId;
    }
    public List<FoodView> getFoods() {
        return foods;
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
    public double getTotalPrice() {
        return totalPrice;
    }
}
