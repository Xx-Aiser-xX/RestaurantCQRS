package restaurant.query.dto;

import restaurant.command.model.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {
    private String id;
    private String customerId;
    private List<FoodDTO> foods;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private double totalPrice;

    public OrderDTO(String id, String customerId, List<FoodDTO> foods, OrderStatus status,
                    LocalDateTime createdAt, LocalDateTime updatedAt, double totalPrice) {
        this.id = id;
        this.customerId = customerId;
        this.foods = foods;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }
    public String getCustomerId() {
        return customerId;
    }
    public List<FoodDTO> getFoods() {
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
