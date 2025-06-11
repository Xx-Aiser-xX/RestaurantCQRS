# Подсистема управления заказами ресторана с использованием CQRS

## Описание проекта
Этот проект представляет собой реализацию подсистемы управления заказами в ресторане, разработанную в соответствии с архитектурным шаблоном CQRS (Command Query Responsibility Segregation). Система позволяет создавать заказы, добавлять и удалять блюда, изменять статус заказа и получать информацию о заказах через консольный интерфейс. Основная цель — разделение операций записи (команд) и чтения (запросов) для обеспечения масштабируемости и согласованности данных.

## Цели и задачи
- Реализовать подсистему управления заказами с применением CQRS.
- Поддерживать операции: создание заказа, добавление/удаление блюд, изменение статуса, просмотр заказов.
- Обеспечить синхронизацию данных между командной и запросной сторонами через события.
- Создать удобный консольный интерфейс для взаимодействия с системой.

## Структура проекта
```
└───src
    ├───Main.java
    │
    └───restaurant
        ├───api
        │   ├───console
        │   │       ConsoleInterface.java
        │   │
        │   └───facade
        │           OrderFacade.java
        │
        ├───command
        │   ├───command
        │   │       AddFoodToOrderCommand.java
        │   │       Command.java
        │   │       CreateOrderCommand.java
        │   │       RemoveFoodFromOrderCommand.java
        │   │       UpdateOrderStatusCommand.java
        │   │
        │   ├───handler
        │   │       AddFoodToOrderCommandHandler.java
        │   │       CommandBus.java
        │   │       CommandHandler.java
        │   │       CreateOrderCommandHandler.java
        │   │       RemoveFoodFromOrderCommandHandler.java
        │   │       UpdateOrderStatusCommandHandler.java
        │   │
        │   ├───model
        │   │       Food.java
        │   │       Order.java
        │   │       OrderStatus.java
        │   │
        │   └───repository
        │           OrderRepository.java
        │
        ├───common
        │   ├───event
        │   │       Event.java
        │   │       EventBus.java
        │   │       FoodAddToOrderEvent.java
        │   │       FoodRemovedFromOrderEvent.java
        │   │       OrderCreatedEvent.java
        │   │       OrderStatusUpdatedEvent.java
        │   │
        │   └───exception
        │           OrderNotFoundException.java
        │
        └───query
            ├───dto
            │       FoodDTO.java
            │       OrderDTO.java
            │
            ├───model
            │       FoodView.java
            │       OrderView.java
            │
            ├───repository
            │       OrderViewRepository.java
            │
            └───service
                    EventHandler.java
                    OrderQueryService.java
```

### Описание ключевых компонентов
- **Командная сторона** (`restaurant.command.*`):  
  Отвечает за операции изменения состояния (создание заказа, добавление/удаление блюд, изменение статуса). Включает модели (`Order`, `Food`), команды (`CreateOrderCommand`, `AddFoodToOrderCommand` и др.), обработчики (`CreateOrderCommandHandler` и др.) и репозиторий (`OrderRepository`).

- **Запросная сторона** (`restaurant.query.*`):  
  Предоставляет данные для чтения (детали заказа, список заказов). Включает модели представления (`OrderView`, `OrderFoodView`), DTO (`OrderDTO`, `OrderFoodDTO`), репозиторий (`OrderViewRepository`) и сервис (`OrderQueryService`).

- **События** (`restaurant.common.event`):  
  Обеспечивают синхронизацию между командной и запросной сторонами через `EventBus`. Включают события: `OrderCreatedEvent`, `FoodAddToOrderEvent`, `FoodRemovedFromOrderEvent`, `OrderStatusUpdatedEvent`.

- **API и интерфейсы** (`restaurant.api.*`):  
  `OrderFacade` объединяет командную и запросную стороны, а `ConsoleInterface` предоставляет консольный интерфейс для взаимодействия.

- **Главный класс** (`Main`):  
  Инициализирует систему и запускает консольный интерфейс.

## Использование
1. **Создание заказа**: Выберите опцию "Создать новый заказ" и введите идентификатор клиента (например, номер стола).
2. **Добавление блюда**: Укажите ID заказа, ID блюда, название, цену и количество.
3. **Удаление блюда**: Введите ID заказа и ID блюда для удаления.
4. **Изменение статуса**: Укажите ID заказа и выберите новый статус (PENDING, COOKING, READY, COMPLETED).
5. **Просмотр заказов**: Используйте опции для просмотра списка заказов или деталей конкретного заказа.

## Особенности реализации
- **CQRS**: Разделение операций записи и чтения для оптимизации производительности и масштабируемости.
- **Событийная модель**: Использование событий для синхронизации данных между командной и запросной сторонами.
- **Консольный интерфейс**: Удобный способ взаимодействия с системой через текстовое меню.
- **Обработка ошибок**: Реализована обработка исключений, таких как `OrderNotFoundException`.