package restaurant.common.event;

public class OrderCreatedEvent extends Event {
    private final String orderId;
    private final String customerId;

    public OrderCreatedEvent(String orderId, String customerId) {
        super();
        this.orderId = orderId;
        this.customerId = customerId;
    }

    public String getOrderId() {
        return orderId;
    }
    public String getCustomerId() {
        return customerId;
    }
}
