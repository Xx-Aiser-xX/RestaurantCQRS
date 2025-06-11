package restaurant.common.event;

import restaurant.command.model.OrderStatus;

public class OrderStatusUpdatedEvent extends Event {
    private final String orderId;
    private final OrderStatus newStatus;

    public OrderStatusUpdatedEvent(String orderId, OrderStatus newStatus) {
        super();
        this.orderId = orderId;
        this.newStatus = newStatus;
    }

    public String getOrderId() {
        return orderId;
    }
    public OrderStatus getNewStatus() {
        return newStatus;
    }
}
