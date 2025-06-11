package restaurant.query.service;

import restaurant.common.event.*;
import restaurant.query.model.OrderView;
import restaurant.query.repository.OrderViewRepository;

public class EventHandler implements EventBus.EventHandler {
    private final OrderViewRepository orderViewRepository;

    public EventHandler(OrderViewRepository orderViewRepository) {
        this.orderViewRepository = orderViewRepository;
    }

    @Override
    public void handle(Event event) {
        if (event instanceof OrderCreatedEvent) {
            handleOrderCreated((OrderCreatedEvent) event);
        } else if (event instanceof FoodAddToOrderEvent) {
            handleFoodAdd((FoodAddToOrderEvent) event);
        } else if (event instanceof FoodRemovedFromOrderEvent) {
            handleFoodRemoved((FoodRemovedFromOrderEvent) event);
        } else if (event instanceof OrderStatusUpdatedEvent) {
            handleOrderStatusUpdated((OrderStatusUpdatedEvent) event);
        }
    }

    private void handleOrderCreated(OrderCreatedEvent event) {
        OrderView orderView = new OrderView(event.getOrderId(), event.getCustomerId(), event.getTimestamp());
        orderViewRepository.save(orderView);
    }

    private void handleFoodAdd(FoodAddToOrderEvent event) {
        OrderView orderView = orderViewRepository.findById(event.getOrderId());
        orderView.addFood(event.getFoodId(), event.getFoodName(), event.getFoodPrice(), event.getQuantity());
        orderViewRepository.save(orderView);
    }

    private void handleFoodRemoved(FoodRemovedFromOrderEvent event) {
        OrderView orderView = orderViewRepository.findById(event.getOrderId());
        orderView.removeFood(event.getFoodId());
        orderViewRepository.save(orderView);
    }

    private void handleOrderStatusUpdated(OrderStatusUpdatedEvent event) {
        OrderView orderView = orderViewRepository.findById(event.getOrderId());
        orderView.updateStatus(event.getNewStatus());
        orderViewRepository.save(orderView);
    }
}
