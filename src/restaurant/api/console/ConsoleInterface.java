package restaurant.api.console;

import restaurant.api.facade.OrderFacade;
import restaurant.command.model.OrderStatus;
import restaurant.common.exception.OrderNotFoundException;
import restaurant.query.dto.OrderDTO;
import restaurant.query.dto.FoodDTO;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ConsoleInterface {
    private final OrderFacade orderFacade;
    private final Scanner in;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    public ConsoleInterface(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
        this.in = new Scanner(System.in);
    }

    public void start() {
        int choice;
        do {
            showMainMenu();
            choice = readIntInput();
            in.nextLine();
            handleMainMenuChoice(choice);
        } while (choice != 0);
    }

    private void showMainMenu() {
        System.out.println("\nсистема заказов ресторана:");
        System.out.println("1. создать новый заказ");
        System.out.println("2. показать все заказы");
        System.out.println("3. информация о заказе");
        System.out.println("4. добавить блюдо в заказ");
        System.out.println("5. удалить блюдо из заказа");
        System.out.println("6. обновить статус заказа");
        System.out.println("7. история заказов");
        System.out.println("0. выход");
        System.out.print("Выберите действие: ");
    }

    private void handleMainMenuChoice(int choice) {
        try {
            switch (choice) {
                case 0:
                    System.out.println("Выход из программы...");
                    break;
                case 1:
                    createOrder();
                    break;
                case 2:
                    showAllOrders();
                    break;
                case 3:
                    showOrderDetails();
                    break;
                case 4:
                    addFoodToOrder();
                    break;
                case 5:
                    removeFoodFromOrder();
                    break;
                case 6:
                    updateOrderStatus();
                    break;
                case 7:
                    historyOrder();
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        } catch (OrderNotFoundException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
    }

    private void createOrder() {
        System.out.print("введите id клиента: ");
        String customerId = in.nextLine().trim();
        orderFacade.createOrder(customerId);
        System.out.println("заказ создан");
    }

    private void showAllOrders() {
        List<OrderDTO> orders = orderFacade.getAllOrders();
        if (orders.isEmpty()) {
            System.out.println("нет доступных заказов");
            return;
        }
        System.out.println("\nвсе заказы");
        System.out.println("id | клиент | статус | сумма");
        System.out.println("--------------------------------------------------------------------------------");
        for (OrderDTO order : orders) {
            System.out.println(order.getId() + " | " +
                            order.getCustomerId() + " | " +
                            order.getStatus() + " | " +
                            order.getTotalPrice());
        }
    }

    private void showOrderDetails() {
        System.out.print("введите id заказа: ");
        String orderId = in.nextLine().trim();
        OrderDTO order = orderFacade.getOrder(orderId);
        System.out.println("\nинформация о заказе");
        System.out.println("id заказа: " + order.getId());
        System.out.println("клиент: " + order.getCustomerId());
        System.out.println("статус: " + order.getStatus());
        System.out.println("создан: " + order.getCreatedAt().format(dateFormatter));
        System.out.println("обновлён: " + order.getUpdatedAt().format(dateFormatter));
        System.out.println("общая сумма: " + String.format("%,.2f руб", order.getTotalPrice()));
        System.out.println("\nсостав заказа:");
        System.out.println("id | блюда | название | цена | кол-во");
        for (FoodDTO food : order.getFoods()) {
            System.out.println(food.getFoodId() + " | " + food.getFoodName() + " | " + food.getFoodPrice() + " | " + food.getQuantity());
        }
    }
    private void addFoodToOrder() {
        System.out.print("введите id заказа: ");
        String orderId = in.nextLine().trim();
        System.out.print("введите id блюда: ");
        String foodId = in.nextLine().trim();
        System.out.print("введите название блюда: ");
        String foodName = in.nextLine().trim();
        System.out.print("введите цену блюда: ");
        double foodPrice = readDoubleInput();
        System.out.print("введите количество: ");
        int quantity = readIntInput();
        in.nextLine();
        orderFacade.addFoodToOrder(orderId, foodId, foodName, foodPrice, quantity);
        System.out.println("блюдо добавлено");
    }

    private void removeFoodFromOrder() {
        System.out.print("введите id заказа: ");
        String orderId = in.nextLine().trim();
        List<FoodDTO> foods = orderFacade.getFoodsInOrder(orderId);
        for (FoodDTO food : foods) {
            System.out.println(food);
        }
        System.out.print("введите id блюда: ");
        String foodId = in.nextLine().trim();
        orderFacade.removeFoodFromOrder(orderId, foodId);
        System.out.println("блюдо удалено");
    }

    private void updateOrderStatus() {
        System.out.print("введите id заказа: ");
        String orderId = in.nextLine().trim();
        System.out.println("выберите новый статус");
        System.out.println("0 созданный");
        System.out.println("1 готовится");
        System.out.println("2 готовый");
        System.out.println("3 выполенный");

        int statusChoice = readIntInput();
        in.nextLine();
        OrderStatus newStatus;
        switch (statusChoice) {
            case 0:
                newStatus = OrderStatus.CREATE;
                break;
            case 1:
                newStatus = OrderStatus.COOKING;
                break;
            case 2:
                newStatus = OrderStatus.READY;
                break;
            case 3:
                newStatus = OrderStatus.COMPLETED;
                break;
            default:
                throw new IllegalArgumentException("неверный выбор статуса");
        }
        orderFacade.updateOrderStatus(orderId, newStatus);
        System.out.println("статус заказа обновлён");
    }

    private void historyOrder(){
        List<OrderDTO> orders = orderFacade.getHistoryOrders();
        for (OrderDTO order : orders){
            System.out.println("создан: " + dateFormatter.format(order.getCreatedAt()) + " id заказа: " + order.getId());
        }
    }

    private int readIntInput() {
        try {
            return in.nextInt();
        } catch (Exception e) {
            in.nextLine();
            return -1;
        }
    }

    private double readDoubleInput() {
        try {
            return in.nextDouble();
        } catch (Exception e) {
            in.nextLine();
            return -1;
        }
    }
}
