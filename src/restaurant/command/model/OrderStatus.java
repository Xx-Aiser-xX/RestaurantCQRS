package restaurant.command.model;

public enum OrderStatus {
    CREATE("созданный"),
    COOKING("готовится"),
    READY("готовый"),
    COMPLETED("выполенный");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
